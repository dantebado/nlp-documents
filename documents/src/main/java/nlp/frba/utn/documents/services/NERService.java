package nlp.frba.utn.documents.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.domain.ner.NERTag;

public interface NERService {
	
	public ResponseEntity<Document> addMainTagToDocumentResponse(String documentId, String tagName);
	public ResponseEntity<Document> addSecondaryTagToDocumentResponse(String documentId, String tagName);
	public ResponseEntity<Page<NERTag>> getAllTags(Pageable pageable);
	public ResponseEntity<NERTag> findByName(String tagName);

}
