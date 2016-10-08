package ch.fhnw.webfr.flashcard.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.fhnw.webfr.flashcard.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByName(String name);

}
