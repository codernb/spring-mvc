package ch.fhnw.webfr.flashcard.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private final String name;
	
	private final List<Questionnaire> questionnaires;
	
	public User(String name, List<Questionnaire> questionnaires) {
		this.name = name;
		this.questionnaires = questionnaires;
	}

	public String getName() {
		return name;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

}
