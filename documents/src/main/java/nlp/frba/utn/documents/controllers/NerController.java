package nlp.frba.utn.documents.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nlp.frba.utn.documents.domain.ner.NERTag;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.repositories.TagsRepository;

@RestController
@RequestMapping({"/ner"})
public class NerController {
	
	@Autowired
	private DocumentRepository documentsRepository;
	
	@Autowired
	private TagsRepository tagsRepository;

	@GetMapping(path = {"/tags"})
	public ResponseEntity<List<NERTag>> getDocumentById() {
		return ResponseEntity.ok(tagsRepository.findAll());
	}

}
