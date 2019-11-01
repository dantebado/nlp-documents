package nlp.frba.utn.documents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/utils"})
public class UtilsController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
	Environment env;
	
	@GetMapping(path = {"/clean"})
	public ResponseEntity<String> clearDatabaseAndPopulateSample(@RequestParam String auth) {
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
