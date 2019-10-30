package nlp.frba.utn.documents.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import nlp.frba.utn.documents.domain.Document;

public interface DocumentRepository extends MongoRepository<Document, String> {
	Optional<Document> findById(String id);
}
