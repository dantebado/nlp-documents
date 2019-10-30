package nlp.frba.utn.documents.domain.ner;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NERAnalysis {
	
	private NERTag mainTag = null;
	Set<NERTag> secondaryTags = new HashSet<NERTag>();

}
