package com.inigo.hernandez.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inigo.hernandez.daos.Answer;
import com.inigo.hernandez.daos.Attempt;
import com.inigo.hernandez.daos.Exam;
import com.inigo.hernandez.daos.Question;
import com.inigo.hernandez.daos.User;
import com.inigo.hernandez.repositories.AnswerRepository;
import com.inigo.hernandez.repositories.AttemptRepository;
import com.inigo.hernandez.repositories.ExamRepository;
import com.inigo.hernandez.repositories.QuestionRepository;
import com.inigo.hernandez.repositories.UserRepository;

@Service
public class ExamService {

	@Autowired
	ExamRepository examRepository;

	@Autowired
	AttemptRepository attemptRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	public Exam getExam(Long examId) {
		return examRepository.findOne(examId);
	}

	public Attempt createAttempt(Long examId, String username) {

		Exam exam = examRepository.findOne(examId);
		User user = userRepository.findOne(username);

		Attempt attempt = new Attempt(exam, user);

		// Set time left for the attempt
		attempt.setTimeLeft(exam.getDuration());

		return attemptRepository.save(attempt);
	}

	public Question getQuestion(Long questionId) {
		return questionRepository.findOne(questionId);
	}

	public List<Answer> getAnswers(Long questionId) {

		return answerRepository.findByQuestionId(questionId);
	}

	public Attempt saveAnswer(Long attemptId, Long answerId) {
		Attempt attempt = attemptRepository.findOne(attemptId);
		Answer answer = answerRepository.findOne(answerId);
		Question question = questionRepository.findOne(answer.getQuestion().getId());

		for (Answer a : answerRepository.findByQuestionId(question.getId()))
			attempt.getAnswers().remove(a);

		attemptRepository.save(attempt);

		attempt.getAnswers().add(answer);

		return attemptRepository.save(attempt);

	}

	public Attempt getAttempt(Long attemptId) {
		return attemptRepository.findOne(attemptId);
	}

	public void calculateScore(Long attemptId) {

		Attempt attempt = attemptRepository.findOne(attemptId);
		Exam exam = examRepository.findOne(attempt.getExam().getId());
		Double questionValue = exam.getTotalScore() / exam.getQuestions().size();

		attempt.setScore(0.00);

		for (Answer answer : attempt.getAnswers()) {
			if (answer.getCorrect() == true)
				attempt.setScore(attempt.getScore() + questionValue);
		}

		attemptRepository.save(attempt);

	}

	public Map<Long, Boolean> getQuestionsAnswered(Long attemptId) {

		Map<Long, Boolean> questionsAnswered = new HashMap<Long, Boolean>();

		Attempt attempt = attemptRepository.findOne(attemptId);
		Exam exam = examRepository.findOne(attempt.getExam().getId());

		for (Question question : exam.getQuestions()) {
			List<Answer> possibleAnswers = answerRepository.findByQuestionId(question.getId());

			// None of the possible answers for this question was selected
			if (Collections.disjoint(attempt.getAnswers(), possibleAnswers))
				questionsAnswered.put(question.getId(), false);
			else
				questionsAnswered.put(question.getId(), true);
		}

		return questionsAnswered;

	}

}
