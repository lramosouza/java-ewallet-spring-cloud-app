package com.lsouza.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lsouza.user.repository.UserRepository;

public class UsernameImpl implements ConstraintValidator<Username, String> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(Username arg0) {
		
	}

	@Override
	public boolean isValid(String content, ConstraintValidatorContext arg1) {
		return !userRepository.existsByUsername(content);
	}

}
