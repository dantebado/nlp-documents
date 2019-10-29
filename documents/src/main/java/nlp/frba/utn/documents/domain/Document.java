package nlp.frba.utn.documents.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
	
	@Id
	private ObjectId _id;
	
	private String subject;
	private String student_name;
	private int year;
	private int quarter;
	private String email;
	private String student_id;

}
