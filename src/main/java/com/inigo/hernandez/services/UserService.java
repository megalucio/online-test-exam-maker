package com.inigo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inigo.hernandez.daos.User;
import com.inigo.hernandez.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public boolean validateUser(String username, String password) {

		User user = userRepository.findOne(username);

		if (user != null && user.getPassword().equals(password))
			return true;
		else
			return false;
	}

}
