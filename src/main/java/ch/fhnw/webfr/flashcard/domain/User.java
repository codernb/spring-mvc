package ch.fhnw.webfr.flashcard.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String name;

	private List<Questionnaire> questionnaires;

	public User() {
		questionnaires = new ArrayList<>();
	}

	public User(String name, List<Questionnaire> questionnaires) {
		this.name = name;
		this.questionnaires = questionnaires;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

}
