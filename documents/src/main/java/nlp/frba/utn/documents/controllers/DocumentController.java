package nlp.frba.utn.documents.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.services.DocumentService;
import nlp.frba.utn.documents.services.NERService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	NERService nerService;
	
	@Autowired
	Environment env; 
	
	/*
	 * 
	 * 
	 * GENERAL
	 * 
	 * 
	 * 
	 * */

	@ApiOperation(value = "Form Document Process", notes = "Processing Document creation form from front")	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> uploadDocument(
			@ApiParam(required = true, value = "File to upload")
			@RequestParam("file") MultipartFile file,
			@ApiParam(required = true, value = "Student Names separated by #", example = "Diego Maradona#Carlos Bianchi")
			@RequestParam("students_names") String _names,
			@ApiParam(required = true, value = "Student IDs separated by #", example = "1578489#1625887")
			@RequestParam("students_ids") String _ids,
			@ApiParam(required = true, value = "File Subject", example = "Economía")
			@RequestParam("subject") String subject,
			@ApiParam(required = true, value = "School Year", example = "2017")
			@RequestParam("year") Integer year,
			@ApiParam(required = true, value = "Quarter (1|2|A)", example = "A")
			@RequestParam("quarter") String quarter,
			@ApiParam(required = true, value = "Email to send analysis results", example = "example@est.frba.utn.edu.ar")
			@RequestParam("email") String email ){
		return documentService.processForm(file, _names, _ids, subject, year, quarter, email);
	}

	@ApiOperation(value = "Create Document", notes = "Creating a Document")
	@PostMapping
	public ResponseEntity<Document> createDocument(
			@Valid @RequestBody Document document){
		return documentService.createNew(document);
	}

	@ApiOperation(value = "Document by ID", notes = "Finding a Document by its ID")
	@GetMapping(path = {"/{documentId}"})
	public ResponseEntity<Document> findDocumentById(
			@ApiParam(required = true, value = "Document ID", example = "086D034BF1D2AA5653B9C9B6122C31572570858104")
			@PathVariable String documentId) {
		return documentService.findByIdResponse(documentId);
	}

	@ApiOperation(value = "Document Content by ID", notes = "Finding a Document by its ID and Retrieving its Contents")
	@GetMapping(path = {"/{documentId}/contents"}, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> fetchDocumentContents(
			@ApiParam(required = true, value = "Document ID", example = "086D034BF1D2AA5653B9C9B6122C31572570858104")
			@PathVariable String documentId) {
		return documentService.fetchDocumentContents(documentId);
	}

	@ApiOperation(value = "Document Keywords by ID", notes = "Finding a Document by its ID and Retrieving its Keywords")
	@GetMapping(path = {"/{documentId}/keywords"})
	public ResponseEntity<String> fetchDocumentKeywords(
			@ApiParam(required = true, value = "Document ID", example = "086D034BF1D2AA5653B9C9B6122C31572570858104")
			@PathVariable String documentId) {
		return documentService.fetchDocumentKeywords(documentId);
	}
	
	@ApiOperation(value = "Documents", notes = "Retrieving collection of all Documents")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Results page you want to retrieve (0..N)", example="1"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Number of records per page.", example="5"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Sorting criteria in the format: property(asc|desc). " +
	                    "Default sort order is ascending. " +
	                    "Multiple sort criteria are supported.", example="id(desc)")
	})
	@GetMapping()
	public ResponseEntity<Page<Document>> getAllDocuments(@ApiIgnore Pageable pageable) {
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
	
	@ApiOperation(value = "Add Main NER Tag", notes = "Adding Main NER Tag to an existing Document")
	@PostMapping(path = {"/{documentId}/ner/tags/main/{tagName}"})
	public ResponseEntity<Document> addMainNERTagToDocument(
			@ApiParam(required = true, value = "Document ID", example="086D034BF1D2AA5653B9C9B6122C31572570858104")
			@PathVariable String documentId,
			@ApiParam(required = true, value = "Tag Name", example="marketing")
			@PathVariable String tagName){
		return nerService.addMainTagToDocumentResponse(documentId, tagName);
	}

	@ApiOperation(value = "Add Secondary NER Tag", notes = "Adding Secondary NER Tag to an existing Document")
	@PostMapping(path = {"/{documentId}/ner/tags/secondary/{tagName}"})
	public ResponseEntity<Document> addSecondaryNERTagToDocument(
			@ApiParam(required = true, value = "Document ID", example="086D034BF1D2AA5653B9C9B6122C31572570858104")
			@PathVariable String documentId,
			@ApiParam(required = true, value = "Tag Name", example="marketing")
			@PathVariable String tagName){
		return nerService.addSecondaryTagToDocumentResponse(documentId, tagName);
	}

}
