package com.inigo.hernandez.tasks;

import com.inigo.hernandez.daos.Attempt;
import com.inigo.hernandez.repositories.AttemptRepository;

public class ExamTimeTask implements Runnable {

	private Attempt attempt;

	private AttemptRepository attemptRepository;

	public ExamTimeTask(Long attemptId, AttemptRepository attemptRepository) {
		this.attempt = attemptRepository.findOne(attemptId);
		this.attemptRepository = attemptRepository;
	}

	public void run() {
		if (attempt.getTimeLeft() >= 0) {
			attempt.setTimeLeft(attempt.getTimeLeft() - 2);
			attemptRepository.save(attempt);
		}

	}

}