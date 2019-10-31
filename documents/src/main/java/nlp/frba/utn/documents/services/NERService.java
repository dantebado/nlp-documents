package nlp.frba.utn.documents.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.domain.ner.NERTag;

public interface NERService {
	
	public ResponseEntity<Document> addMainTagToDocumentResponse(String documentId, String tagName);
	public ResponseEntity<Document> addSecondaryTagToDocumentResponse(String documentId, String tagName);
	public ResponseEntity<List<NERTag>> getAllTags();

}
