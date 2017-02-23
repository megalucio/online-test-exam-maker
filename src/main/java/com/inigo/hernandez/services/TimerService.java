package com.inigo.hernandez.services;

import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.inigo.hernandez.repositories.AttemptRepository;
import com.inigo.hernandez.tasks.ExamTimeTask;

@Service
public class TimerService {

	@Autowired
	private TaskScheduler taskScheduler;

	@Autowired
	private AttemptRepository attemptRepository;

	private ScheduledFuture<?> task;

	public void startTimer(Long attemptId) {

		task = taskScheduler.scheduleAtFixedRate(new ExamTimeTask(attemptId, attemptRepository), 2000);
	}

	public void stopTimer() {
		task.cancel(false);
	}

}
