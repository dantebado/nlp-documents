package nlp.frba.utn.documents.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import nlp.frba.utn.documents.domain.security.Role;
import nlp.frba.utn.documents.domain.security.Role.RoleName;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}