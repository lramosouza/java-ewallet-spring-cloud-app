package com.lsouza.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenDTO extends BaseDTO {
	
	private String id;
	
	private String walletId;
	
	private String token;
	
	@Builder
	private TokenDTO(Integer code, String returnMessage) {
		super(code,returnMessage);
	}
}
