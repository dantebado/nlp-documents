package nlp.frba.utn.documents.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import nlp.frba.utn.documents.domain.DocumentBasis;
import nlp.frba.utn.documents.domain.Student;
import nlp.frba.utn.documents.domain.test.RouteTest;
import nlp.frba.utn.documents.utils.JSONUtil;

@Controller
public class DocumentUploadController {
	
	@Autowired
	Environment env; 

	@RequestMapping(value = "/processNewDocument", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DocumentBasis> uploadDocument(
			@RequestParam("file") MultipartFile file,
			@RequestParam("students_names") String _names,
			@RequestParam("students_ids") String _ids,
			@RequestParam("subject") String subject,
			@RequestParam("year") Integer year,
			@RequestParam("quarter") String quarter,
			@RequestParam("email") String email ){
		
		String[] names = _names.split("#");
		String[] ids   = _ids.split("#");
		
		DocumentBasis db = null;
		
		List<Student> students = new ArrayList<Student>();
		if(names.length == ids.length) {
			for(int i=0 ; i<names.length ; i++) {
				students.add(new Student(names[i], ids[i]));
			}
		}

		try {
			final String uploadDir = env.getProperty("store.local.documents-relative-path");
			final String realPath = env.getProperty("store.local.absolute-path") + uploadDir;
			String extension = "";
			int i = file.getOriginalFilename().lastIndexOf('.');
			if (i > 0) {
			    extension = file.getOriginalFilename().substring(i+1);
			}
			final String newFileName = UUID.randomUUID().toString() + "." + extension;
			final File transferFile = new File(realPath + newFileName);
			
			System.out.println("New File " + realPath + newFileName);
			
			file.transferTo(transferFile);
			
			db = new DocumentBasis(subject, students, year, quarter, email, file.getOriginalFilename(), "%PATH_TO_FOLDER%" + newFileName);
			
			ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			String json = objectMapper.writeValueAsString(db);
			
			RestTemplate restTemplate = new RestTemplate();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
		    ResponseEntity<String> out = restTemplate.exchange("http://localhost:" + env.getProperty("server.port") + "/documents",
		    											HttpMethod.POST, entity, String.class);
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.status(201).body(db);
	}

}