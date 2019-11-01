package nlp.frba.utn.documents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nlp.frba.utn.documents.domain.ner.NERTag;
import nlp.frba.utn.documents.services.NERService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping({"/ner"})
public class NerController {
	
	@Autowired
	NERService nerService;
	
	@ApiOperation(value = "NER Tags", notes = "Retrieving collection of all NER Tags")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Results page you want to retrieve (0..N)", example="1"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Number of records per page.", example="5"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Sorting criteria in the format: property(asc|desc). " +
	                    "Default sort order is ascending. " +
	                    "Multiple sort criteria are supported.", example="name(desc)")
	})
	@GetMapping(path = {"/tags"})
	public ResponseEntity<Page<NERTag>> getAllTags(
			@ApiIgnore
			Pageable pageable) {
		return nerService.getAllTags(pageable);
	}
	
	@ApiOperation(value = "NER Tag by Name", notes = "Finding a NER Tag by its name")
	@GetMapping(path = {"/tags/{tagName}"})
	public ResponseEntity<NERTag> getTagByName(
			@ApiParam(required = true, value = "NER Tag Name", example="marketing")
			@PathVariable String tagName) {
		return nerService.findByName(tagName);
	}

}

