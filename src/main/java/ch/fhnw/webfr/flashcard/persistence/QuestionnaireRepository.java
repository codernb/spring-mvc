package ch.fhnw.webfr.flashcard.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;

@Repository
public class QuestionnaireRepository {

	private static final Log LOG = LogFactory.getLog(QuestionnaireRepository.class);

	private List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();

	@PostConstruct
	private void postConsturct() {
		save(new Questionnaire("Test Questionnaire 1", "Lorem ipsum dolor sit amet..."));
		save(new Questionnaire("Test Questionnaire 2", "Lorem ipsum dolor sit amet..."));
		save(new Questionnaire("i18n Test fünf", "Lorem ipsum dolor sit amet..."));
		LOG.debug("Questionnaires initialized successfully");
	}

	public Long save(Questionnaire q) {
		Long id = new Long(questionnaires.size());
		q.setId(id);
		questionnaires.add(q);
		return id;
	}

	public List<Questionnaire> findAll() {
		return questionnaires;
	}

	public Questionnaire findById(Long id) {
		return questionnaires.get(id.intValue());
	}

	public void clear() {
		questionnaires = new ArrayList<Questionnaire>();
	}
}
