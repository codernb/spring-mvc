package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnareController {

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@GetMapping
	public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
		handleQuestionnairesRequest(request, response);
	}

	@GetMapping("/{id}")
	public void findById(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		handleQuestionnairesRequest(request, response, id);
	}

	private void handleQuestionnairesRequest(HttpServletRequest request, HttpServletResponse response, String id)
			throws IOException {
		List<Questionnaire> questionnaires = new ArrayList<>();
		questionnaires.add(questionnaireRepository.findOne(id));
		handleQuestionnairesRequest(request, response, questionnaires);
	}

	private void handleQuestionnairesRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
		handleQuestionnairesRequest(request, response, questionnaires);
	}

	private void handleQuestionnairesRequest(HttpServletRequest request, HttpServletResponse response,
			List<Questionnaire> questionnaires) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html><head><title>Example</title></head><body>");
		writer.append("<h3>Fragebögen</h3>");
		for (Questionnaire questionnaire : questionnaires) {
			if (questionnaire == null)
				continue;
			String url = request.getContextPath() + "/questionnaires/" + questionnaire.getId().toString();
			writer.append("<p><a href='" + response.encodeURL(url) + "'>" + questionnaire.getTitle() + "</a></p>");
		}
		writer.append("</body></html>");
	}

}
