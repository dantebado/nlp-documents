package nlp.frba.utn.documents.domain;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentBasis {

	private String subject;
	Collection<Student> students;
	private Integer year;
	private String quarter;
	private String email;
	private String documentName;
	private String documentURI;

}
