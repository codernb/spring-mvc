package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnareController {

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("questionnaires", questionnaireRepository.findAll());
		return "questionnaires/list";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable String id, Model model) throws IOException {
		model.addAttribute("questionnaires", questionnaireRepository.findOne(id));
		return "questionnaires/list";
	}

}
