package com.lsouza.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	
	private String message;
}
