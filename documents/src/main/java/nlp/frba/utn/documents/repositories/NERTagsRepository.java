package nlp.frba.utn.documents.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import nlp.frba.utn.documents.domain.ner.NERTag;

public interface NERTagsRepository extends MongoRepository<NERTag, String> {
	Optional<NERTag> findByName(String name);
}
