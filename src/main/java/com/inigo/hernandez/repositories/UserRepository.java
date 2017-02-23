package com.inigo.hernandez.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inigo.hernandez.daos.User;

public interface UserRepository extends CrudRepository<User, String> {

}
