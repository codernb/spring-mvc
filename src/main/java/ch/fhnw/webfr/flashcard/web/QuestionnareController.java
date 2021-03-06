package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
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
	public String findById(Model model, @PathVariable String id) throws IOException {
		Questionnaire questionnaire = questionnaireRepository.findOne(id);
		if (questionnaire == null)
			return "404";
		model.addAttribute("questionnaire", questionnaire);
		return "questionnaires/show";
	}

	@GetMapping("/create")
	public String create(Model model, @RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") String description) {
		model.addAttribute("questionnaire", new Questionnaire(title, description));
		return "questionnaires/create";
	}

	@PostMapping
	public String create(@Valid Questionnaire questionnaire, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "questionnaires/create";
		questionnaire = questionnaireRepository.save(questionnaire);
		return String.format("redirect:/questionnaires/%s", questionnaire.getId());
	}

	@GetMapping("/{id}/update")
	public String update(Model model, @PathVariable String id) {
		model.addAttribute("questionnaire", questionnaireRepository.findOne(id));
		return "questionnaires/update";
	}

	@PutMapping
	public String update(@Valid Questionnaire questionnaire, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "/questionnaires/update";
		questionnaire = questionnaireRepository.save(questionnaire);
		return String.format("redirect:/questionnaires/%s", questionnaire.getId());
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		if (!questionnaireRepository.exists(id))
			return "404";
		questionnaireRepository.delete(id);
		return "redirect:/questionnaires";
	}

}
