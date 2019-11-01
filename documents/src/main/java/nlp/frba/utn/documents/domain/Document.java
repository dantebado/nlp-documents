package nlp.frba.utn.documents.domain;

import java.util.Collection;

import org.springframework.core.env.Environment;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nlp.frba.utn.documents.domain.ner.NERAnalysis;
import nlp.frba.utn.documents.utils.BeanUtil;

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
	@JsonIgnore
	private String documentURI;
	
	private NERAnalysis ner = new NERAnalysis();
	
	@Transient
	@JsonProperty("document_uri")
	public String formattedDocumentUri() {
		Environment env = BeanUtil.getBean(Environment.class);
		return documentURI.replace("%PATH_TO_FOLDER%", "http://" + env.getProperty("aws.publicip") + ":" + env.getProperty("server.port") + "/" +
															env.getProperty("store.local.external-access") + env.getProperty("store.local.documents-relative-path"));
	}

}
