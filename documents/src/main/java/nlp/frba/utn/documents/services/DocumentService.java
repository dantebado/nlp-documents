package nlp.frba.utn.documents.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import nlp.frba.utn.documents.domain.Document;

public interface DocumentService {
	
	public ResponseEntity<Page<Document>> getAllResponse(Pageable pageable);
	public ResponseEntity<Document> findByIdResponse(String documentId);
	public ResponseEntity<Document> createNew(Document document);

}
