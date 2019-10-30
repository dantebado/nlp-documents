package nlp.frba.utn.documents.controllers;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.domain.ner.NERTag;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.repositories.TagsRepository;
import nlp.frba.utn.documents.utils.MD5Util;

@RestController
@RequestMapping({"/documents"})
public class DocumentController {
	
	@Autowired
	private DocumentRepository documentsRepository;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@PostMapping
	public ResponseEntity<Document> create(@Valid @RequestBody Document document){
		if(document.getId() == null) {
			try {
				document.setId( MD5Util.getMD5(document.getDocumentName()) + System.currentTimeMillis() );
			} catch (NoSuchAlgorithmException e) {
				return ResponseEntity.status(500).build();
			}
		}
		
		documentsRepository.save(document);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{documentId}")
                .buildAndExpand(document.getId())
                .toUri();
		
		return ResponseEntity.created(location).body(document);
	}

	@GetMapping(path = {"/{documentId}"})
	public ResponseEntity<Document> getDocumentById(@PathVariable String documentId) {
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			return ResponseEntity.ok(document.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping()
	public ResponseEntity<List<Document>> getDocuments() {
		return ResponseEntity.ok(documentsRepository.findAll());
	}
	
	@PostMapping(path = {"/{documentId}/ner/tags/main/{tagName}"})
	public ResponseEntity<Document> createNerMainTag(@PathVariable String documentId, @PathVariable String tagName){
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			Document documentToAdd = document.get();
			
			Optional<NERTag> tag = tagsRepository.findByName(tagName);
			NERTag tagToAdd = null;
			if(tag.isPresent()) {
				tagToAdd = tag.get();
			} else {
				tagToAdd = new NERTag(tagName);
			}
			
			documentToAdd.getNer().setMainTag(tagToAdd);
			
			tagsRepository.save(tagToAdd);
			documentsRepository.save(documentToAdd);
			
			return ResponseEntity.ok(documentToAdd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(path = {"/{documentId}/ner/tags/secondary/{tagName}"})
	public ResponseEntity<Document> createNerSecondaryTag(@PathVariable String documentId, @PathVariable String tagName){
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			Document documentToAdd = document.get();
			
			Optional<NERTag> tag = tagsRepository.findByName(tagName);
			NERTag tagToAdd = null;
			if(tag.isPresent()) {
				tagToAdd = tag.get();
			} else {
				tagToAdd = new NERTag(tagName);
			}
			
			documentToAdd.getNer().getSecondaryTags().add(tagToAdd);
			
			tagsRepository.save(tagToAdd);
			documentsRepository.save(documentToAdd);
			
			return ResponseEntity.ok(documentToAdd);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
