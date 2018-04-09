package com.lsouza.wallet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "wallet")
public class Wallet {
	
	@Id
	private String id;
	
	private String userId;
	
	private String cardholder;
	
	private String cardNumber;
	
	private Integer expirationMonth;
	
	private Integer expirationYear;
		
	private String bin;
	
	private String last4;
	
	private boolean tokenized;
}
