package com.lsouza.user.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.lsouza.user.consts.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseDTO {
	
	
	private Integer code = HttpStatus.OK.value();
	
	private String returnMessage = Constants.SUCESSFULLY;
	
	private LocalDateTime requestDateTime = LocalDateTime.now();
	
	public BaseDTO(Integer code,String returnMessage){
		this.code = code;
		this.returnMessage = returnMessage;
	}
}
