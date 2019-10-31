package nlp.frba.utn.documents.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.services.DocumentService;
import nlp.frba.utn.documents.services.NERService;

@RestController
@RequestMapping({"/documents"})
public class DocumentController {
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	NERService nerService;
	
	/*
	 * 
	 * 
	 * GENERAL
	 * 
	 * 
	 * 
	 * */
	
	@PostMapping
	public ResponseEntity<Document> create(@Valid @RequestBody Document document){
		return documentService.createNew(document);
	}

	@GetMapping(path = {"/{documentId}"})
	public ResponseEntity<Document> findDocumentById(@PathVariable String documentId) {
		return documentService.findByIdResponse(documentId);
	}

	@GetMapping()
	public ResponseEntity<Page<Document>> getDocuments(Pageable pageable) {
		return documentService.getAllResponse(pageable);
	}
	

	/*
	 * 
	 * 
	 * NER
	 * 
	 * 
	 * 
	 * */
	
	@PostMapping(path = {"/{documentId}/ner/tags/main/{tagName}"})
	public ResponseEntity<Document> createNerMainTag(@PathVariable String documentId, @PathVariable String tagName){
		return nerService.addMainTagToDocumentResponse(documentId, tagName);
	}
	
	@PostMapping(path = {"/{documentId}/ner/tags/secondary/{tagName}"})
	public ResponseEntity<Document> createNerSecondaryTag(@PathVariable String documentId, @PathVariable String tagName){
		return nerService.addSecondaryTagToDocumentResponse(documentId, tagName);
	}

}
