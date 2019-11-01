package nlp.frba.utn.documents.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DocumentForLoad {
	
	String subject;
	Collection<Student> students;
	Integer year;
	String quarter;
	String email;
	String document_name;
	String document_uri;

}
