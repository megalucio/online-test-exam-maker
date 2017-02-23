package com.inigo.hernandez.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inigo.hernandez.daos.Attempt;

public interface AttemptRepository extends CrudRepository<Attempt, Long> {
	
}
