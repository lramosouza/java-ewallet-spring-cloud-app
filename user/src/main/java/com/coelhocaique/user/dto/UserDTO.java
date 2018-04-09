package com.lsouza.user.dto;

import javax.validation.constraints.NotNull;

import com.lsouza.user.validation.Username;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO{
	
	private String id;
	
	@NotNull(message = "Username must be informed.")
	@Username
	private String username;
	
	private String key;
	
	@Builder
	private UserDTO(Integer code, String returnMessage) {
		super(code,returnMessage);
	}
}
