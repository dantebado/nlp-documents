package nlp.frba.utn.documents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nlp.frba.utn.documents.domain.ner.NERTag;
import nlp.frba.utn.documents.services.NERService;

@RestController
@RequestMapping({"/ner"})
public class NerController {
	
	@Autowired
	NERService nerService;

	@GetMapping(path = {"/tags"})
	public ResponseEntity<Page<NERTag>> getTags(Pageable pageable) {
		return nerService.getAllTags(pageable);
	}
	
	@GetMapping(path = {"/tags/{tagName}"})
	public ResponseEntity<NERTag> getTagByName(@PathVariable String tagName) {
		return nerService.findByName(tagName);
	}

}
