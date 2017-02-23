package com.inigo.hernandez.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inigo.hernandez.daos.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	List<Question> findAll();

}
