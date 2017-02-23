package com.inigo.hernandez.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.inigo.hernandez.daos.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

	List<Answer> findByQuestionId(Long questionId);

}
