package nlp.frba.utn.documents.exceptions.classes;

import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties({ "suppressed", "localized_message", "stack_trace", "cause" })
public class GenericException extends RuntimeException {
	private static final long serialVersionUID = 369839696490698262L;
	
	String exceptionId;
	String message;
	HashMap<String, Object> details;
	
	public GenericException(String message) {
		this.exceptionId = UUID.randomUUID().toString();
		this.message = message;
		this.details = new HashMap<String, Object>();
	}
	
	protected void addDetail(String key, Object value) {
		this.details.put(key, value);
	}

}
