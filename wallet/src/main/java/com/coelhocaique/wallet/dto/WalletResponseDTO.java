package com.lsouza.wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WalletResponseDTO extends BaseDTO {
	
	private String id;
	
	private String bin;
	
	private String last4;
	
	private Boolean tokenized;
	
	@Builder
	public WalletResponseDTO(String id, String bin, String last4,Boolean tokenized) {
		this.id = id;
		this.bin = bin;
		this.last4 = last4;
		this.tokenized = tokenized;
	}
}
