package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.fhnw.webfr.flashcard.domain.User;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.persistence.UserRepository;

@Controller
public class PageController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@GetMapping("/")
	public String index() throws IOException {
		return "redirect:questionnaires";
	}

	@ResponseBody
	@GetMapping("/hello")
	public String hello(@RequestParam("name") String name) {
		User user = userRepository.findOne(name);
		return user != null ? MessageFormat.format("Hello {0}<br/>You have {1} questionnaires in your repo.", name,
				user.getQuestionnaires().size()) : "Sorry, no user found";
	}

	@ResponseBody
	@GetMapping("/create")
	public String create(@RequestParam("name") String name) throws MissingServletRequestParameterException {
		if (name.isEmpty())
			throw new MissingServletRequestParameterException("name", String.class.getSimpleName());
		userRepository.save(new User(name, questionnaireRepository.findAll().stream().filter(q -> Math.random() < 0.5)
				.collect(Collectors.toList())));
		return "created";
	}

}
