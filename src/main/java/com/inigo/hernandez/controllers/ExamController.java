package com.inigo.hernandez.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inigo.hernandez.daos.Answer;
import com.inigo.hernandez.daos.Attempt;
import com.inigo.hernandez.daos.Exam;
import com.inigo.hernandez.daos.Question;
import com.inigo.hernandez.models.HomeModel;
import com.inigo.hernandez.models.QuestionModel;
import com.inigo.hernandez.services.ExamService;
import com.inigo.hernandez.services.TimerService;
import com.inigo.hernandez.services.UserService;

@Controller
@SessionAttributes("time")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private UserService userService;

	@Autowired
	private TimerService timerService;

	/**
	 * <p>
	 * Show exam description and login form
	 * </p>
	 * 
	 * @param examId
	 *            the id of the exam
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String doGetHome(@RequestParam("examId") Long examId, Model model) {

		Exam exam = examService.getExam(examId);

		model.addAttribute("exam", exam);

		return "home";
	}

	/**
	 * <p>
	 * Process login information and redirects to actual exam
	 * </p>
	 * 
	 * @param homeModel
	 *            the login info form the home page
	 * @param session
	 *            the http session object in order to set up the time of the
	 *            exam
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String doPostHome(@ModelAttribute("homeModel") HomeModel homeModel) {

		// If user and pass are correct go to first question of exam
		if (userService.validateUser(homeModel.getUsername(), homeModel.getPassword())) {

			Attempt attempt = examService.createAttempt(homeModel.getExamId(), homeModel.getUsername());

			// Start timer to include exam duration in the attempt
			timerService.startTimer(attempt.getId());

			return "redirect:/questions/" + attempt.getId() + "/" + attempt.getExam().getQuestions().get(0).getId();

			// Otherwise show the login page for this exam again
		} else
			return "redirect:/home?examId=" + homeModel.getExamId();

	}

	/**
	 * <p>
	 * Shows question
	 * </p>
	 * 
	 * @param attemptId
	 * @param questionId
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping(value = "/questions/{attemptId}/{questionId}", method = RequestMethod.GET)
	public String doGetQuestion(@PathVariable("attemptId") Long attemptId, @PathVariable("questionId") Long questionId,
			Model model) {

		Question question = examService.getQuestion(questionId);
		List<Answer> answers = examService.getAnswers(questionId);
		Attempt attempt = examService.getAttempt(attemptId);
		Exam exam = examService.getExam(attempt.getExam().getId());

		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		model.addAttribute("attempt", attempt);
		model.addAttribute("exam", exam);

		if (question == null)
			return "redirect:/end/" + attemptId;
		else
			return "question";
	}

	/**
	 * <p>
	 * Processes question answer
	 * </p>
	 * 
	 * @param questionModel
	 *            contains the information about the selected answer
	 * @param attemptId
	 * @param questionId
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping(value = "/questions/{attemptId}/{questionId}", method = RequestMethod.POST)
	public String doPostQuestion(@ModelAttribute("questionModel") QuestionModel questionModel,
			@PathVariable("attemptId") Long attemptId, @PathVariable("questionId") Long questionId) {

		if (questionModel.getAnswerId() != null)
			examService.saveAnswer(questionModel.getAttemptId(), questionModel.getAnswerId());

		return "redirect:/questions/" + attemptId + "/" + questionId;
	}

	/**
	 * <p>
	 * Shows final page where you can submit results
	 * </p>
	 * 
	 * @param questionModel
	 *            contains the information about the selected answer
	 * @param attemptId
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping("/end/{attemptId}")
	public String doEnd(@PathVariable("attemptId") Long attemptId, Model model) {
		 
		Attempt attempt = examService.getAttempt(attemptId);
		Map<Long,Boolean> quesionsAnswered = examService.getQuestionsAnswered(attemptId);

		model.addAttribute("attempt", attempt);
		model.addAttribute("questionsAnswered", quesionsAnswered);

		return "end";
	}

	/**
	 * <p>
	 * Shows results
	 * </p>
	 * 
	 * @param questionModel
	 *            contains the information about the selected answer
	 * @param attemptId
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping("/results/{attemptId}")
	public String doResults(@PathVariable("attemptId") Long attemptId, Model model) {

		// Stop counting the time for this exam as you already finished
		timerService.stopTimer();

		examService.calculateScore(attemptId);

		Attempt attempt = examService.getAttempt(attemptId);
		Exam exam = examService.getExam(attempt.getExam().getId());

		model.addAttribute("attempt", attempt);
		model.addAttribute("exam", exam);

		return "results";
	}

}
