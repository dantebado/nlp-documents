package nlp.frba.utn.documents.controllers;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.repositories.DocumentRepository;

@RestController
@RequestMapping({"/documents"})
public class DocumentController {
	
	private DocumentRepository repository;

	DocumentController(DocumentRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public Document create(@Valid @RequestBody Document document){
		document.set_id(ObjectId.get());
		return repository.save(document);
	}

}
