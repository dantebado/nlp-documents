package nlp.frba.utn.documents.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import nlp.frba.utn.documents.exceptions.classes.DocumentNotFoundException;
import nlp.frba.utn.documents.exceptions.classes.NERTagNotFoundException;
import nlp.frba.utn.documents.exceptions.classes.UnknownErrorException;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = DocumentNotFoundException.class)
	public ResponseEntity<Object> exception(DocumentNotFoundException exception) {
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UnknownErrorException.class)
	public ResponseEntity<Object> exception(UnknownErrorException exception) {
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NERTagNotFoundException.class)
	public ResponseEntity<Object> exception(NERTagNotFoundException exception) {
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}

}
