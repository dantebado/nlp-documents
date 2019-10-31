package nlp.frba.utn.documents.domain;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nlp.frba.utn.documents.domain.ner.NERAnalysis;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
	
	@Id
	private String id;
	
	Collection<Student> students;
	private String subject;
	private Integer year;
	private String quarter;
	private String email;
	private String documentName;
	private String documentURI;
	
	private NERAnalysis ner = new NERAnalysis();

}
