package nlp.frba.utn.documents.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import nlp.frba.utn.documents.domain.Document;

public interface DocumentRepository extends MongoRepository<Document, String> {
	Document findBy_id(ObjectId _id);
}
