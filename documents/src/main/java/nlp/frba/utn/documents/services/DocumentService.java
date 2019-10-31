package nlp.frba.utn.documents.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nlp.frba.utn.documents.domain.Document;

public interface DocumentService {
	
	public ResponseEntity<List<Document>> getAllResponse();
	public ResponseEntity<Document> findByIdResponse(String documentId);
	public ResponseEntity<Document> createNew(Document document);

}
