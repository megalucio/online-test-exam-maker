package com.inigo.hernandez.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inigo.hernandez.daos.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {
	
}
