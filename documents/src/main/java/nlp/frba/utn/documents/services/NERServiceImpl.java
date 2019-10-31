package nlp.frba.utn.documents.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.domain.ner.NERTag;
import nlp.frba.utn.documents.exceptions.classes.DocumentNotFoundException;
import nlp.frba.utn.documents.exceptions.classes.NERTagNotFoundException;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.repositories.NERTagsRepository;

@Service
public class NERServiceImpl implements NERService {
	
	@Autowired
	private DocumentRepository documentsRepository;
	
	@Autowired
	private NERTagsRepository nerTagsRepository;
	
	private NERTag findOrCreateNERTag(String tagName) {		
		Optional<NERTag> tag = nerTagsRepository.findByName(tagName);
		NERTag tagToAdd = null;
		if(tag.isPresent()) {
			tagToAdd = tag.get();
		} else {
			tagToAdd = new NERTag(tagName);
		}
		return tagToAdd;
	}

	@Override
	public ResponseEntity<Document> addMainTagToDocumentResponse(String documentId, String tagName) {
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			Document documentToAdd = document.get();
			NERTag tagToAdd = findOrCreateNERTag(tagName);
			
			documentToAdd.getNer().setMainTag(tagToAdd);
			
			nerTagsRepository.save(tagToAdd);
			documentsRepository.save(documentToAdd);
			
			return ResponseEntity.ok(documentToAdd);
		} else {
			throw( new DocumentNotFoundException(documentId) );
		}
	}

	@Override
	public ResponseEntity<Document> addSecondaryTagToDocumentResponse(String documentId, String tagName) {
		Optional<Document> document = documentsRepository.findById(documentId);
		if(document.isPresent()) {
			Document documentToAdd = document.get();
			NERTag tagToAdd = findOrCreateNERTag(tagName);
			
			documentToAdd.getNer().getSecondaryTags().add(tagToAdd);
			
			nerTagsRepository.save(tagToAdd);
			documentsRepository.save(documentToAdd);
			
			return ResponseEntity.ok(documentToAdd);
		} else {
			throw( new DocumentNotFoundException(documentId) );
		}
	}

	@Override
	public ResponseEntity<Page<NERTag>> getAllTags(Pageable pageable) {
		return ResponseEntity.ok(nerTagsRepository.findAll(pageable));
	}

	@Override
	public ResponseEntity<NERTag> findByName(String tagName) {
		Optional<NERTag> tag = nerTagsRepository.findByName(tagName);
		if(tag.isPresent()) {
			return ResponseEntity.ok(tag.get());
		} else {
			throw( new NERTagNotFoundException(tagName) );
		}
	}

}
