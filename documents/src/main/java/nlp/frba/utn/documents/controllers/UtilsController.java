package nlp.frba.utn.documents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/utils", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilsController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
	Environment env;

	@ApiOperation(value = "Clean Database", notes = "Cleaning all records from Document database")
	@GetMapping(path = {"/clean"})
	public ResponseEntity<String> clearDatabaseAndPopulateSample(
			@ApiParam(required = true, value = "Access password", example="password")
			@RequestParam String auth) {
		if(passwordEncoder.matches(auth, env.getProperty("local.security.auth"))) {
			for (String collectionName : mongoTemplate.getCollectionNames()) {
	            if (!collectionName.startsWith("system.")) {
	                mongoTemplate.getCollection(collectionName).drop();
	            }
	        }
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
