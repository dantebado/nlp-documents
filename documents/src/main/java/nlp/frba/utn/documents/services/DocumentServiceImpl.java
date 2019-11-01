package nlp.frba.utn.documents.services;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.domain.DocumentBasis;
import nlp.frba.utn.documents.domain.Student;
import nlp.frba.utn.documents.exceptions.classes.DocumentNotFoundException;
import nlp.frba.utn.documents.exceptions.classes.UnknownErrorException;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.utils.MD5Util;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentsRepository;
	
	@Autowired
	private Environment env;

	@Override
	public ResponseEntity<Page<Document>> getAllResponse(Pageable pageable) {
		return ResponseEntity.ok( documentsRepository.findAll(pageable) );
	}

	@Override
	public ResponseEntity<Document> findByIdResponse(String documentId) {
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			return ResponseEntity.ok(document.get());
		} else {
			throw( new DocumentNotFoundException(documentId) );
		}
	}

	@Override
	public ResponseEntity<Document> createNew(Document document) {
		if(document.getId() == null) {
			try {
				document.setId( MD5Util.getMD5(document.getDocumentName()) + System.currentTimeMillis() );
			} catch (NoSuchAlgorithmException e) {
				throw( new UnknownErrorException() );
			}
		}
		
		documentsRepository.save(document);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{documentId}")
                .buildAndExpand(document.getId())
                .toUri();
		
		return ResponseEntity.created(location).body(document);
	}

	@Override
	public ResponseEntity<String> processForm(MultipartFile file, String _names,
	String _ids, String subject, Integer year, String quarter, String email) {		
		String[] names = _names.split("#");
		String[] ids   = _ids.split("#");
		
		DocumentBasis db = null;
		
		List<Student> students = new ArrayList<Student>();
		if(names.length == ids.length) {
			for(int i=0 ; i<names.length ; i++) {
				students.add(new Student(names[i], ids[i]));
			}
		}
		
		final String uploadDir = env.getProperty("store.local.documents-relative-path");
		final String realPath = env.getProperty("store.local.absolute-path") + uploadDir;
		String extension = "";
		int i = file.getOriginalFilename().lastIndexOf('.');
		if (i > 0) {
		    extension = file.getOriginalFilename().substring(i+1);
		}
		final String newFileName = UUID.randomUUID().toString() + "." + extension;
		final File transferFile = new File(realPath + newFileName);
		
		try {
			file.transferTo(transferFile);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
			throw( new UnknownErrorException() );
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw( new UnknownErrorException() );
		}
		
		db = new DocumentBasis(subject, students, year, quarter, email, file.getOriginalFilename(), "%PATH_TO_FOLDER%" + newFileName);
		
		ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		String json;
		try {
			json = objectMapper.writeValueAsString(db);
		} catch (JsonProcessingException e) {
			throw( new UnknownErrorException() );
		}
		
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
	    try {
		    ResponseEntity<String> out = restTemplate.exchange(env.getProperty("aws.public-address") + "s:" + env.getProperty("server.port") + "/documents",
					HttpMethod.POST, entity, String.class);
			return ResponseEntity.status(out.getStatusCode()).headers(headers).body(out.getBody());
		} catch (RestClientException e) {
			System.out.println(e.getMessage());
			throw( new UnknownErrorException() );
		}			
		
	}

}
