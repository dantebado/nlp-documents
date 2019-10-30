package nlp.frba.utn.documents.domain.ner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nlp.frba.utn.documents.domain.Document;
import nlp.frba.utn.documents.repositories.DocumentRepository;
import nlp.frba.utn.documents.utils.BeanUtil;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NERTag {
	
	@Id
	String name; 
	
	@Transient
	@JsonProperty("has_as_main_tag")
	public Collection<String> hasAsMainTag() {
		DocumentRepository documentsRepository = BeanUtil.getBean(DocumentRepository.class);
		
		List<String> hits = new ArrayList<String>();
		
		List<Document> documents = documentsRepository.findAll();
		for(Document d : documents) {
			try {
				if(d.getNer() != null) {
					if(d.getNer().getMainTag().getName().equalsIgnoreCase(name)) {
						hits.add(d.getId());
					}
				}
			} catch (Exception e) {
			}
		}
		
		return hits;
	}
	
	@Transient
	@JsonProperty("has_as_secondary_tag")
	public Collection<String> hasAsSecondaryTag() {
		DocumentRepository documentsRepository = BeanUtil.getBean(DocumentRepository.class);
		
		List<String> hits = new ArrayList<String>();
		
		List<Document> documents = documentsRepository.findAll();
		for(Document d : documents) {
			for(NERTag t : d.getNer().getSecondaryTags()) {
				if(t.getName().equalsIgnoreCase(name)) {
					hits.add(d.getId());
				}
			}
		}
		
		return hits;
	}

}
