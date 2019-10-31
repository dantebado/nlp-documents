package nlp.frba.utn.documents.services;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.exceptions.classes.DocumentNotFoundException;
import nlp.frba.utn.documents.exceptions.classes.UnknownErrorException;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.utils.MD5Util;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentsRepository;

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
	
	

}
