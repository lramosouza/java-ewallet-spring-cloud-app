package com.lsouza.wallet.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import com.lsouza.wallet.consts.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WalletRequestDTO{
	
	@NotNull(message = Constants.CARDHOLDER_NOT_NULL)
	@Length(max = 50,message = Constants.CARDHOLDER_LENGTH)
	private String cardholder;
	
	@NotNull(message = Constants.CARDNUMBER_NOT_NULL)
	@CreditCardNumber(message = Constants.CARDNUMBER_INVALID)
	private String cardNumber;
	
	@NotNull(message = Constants.EXPIRATION_MONTH_NOT_NULL)
	@Length(max = 2, min = 2, message = Constants.EXPIRATION_MONTH_LENGTH)
	@Pattern(regexp = "\\d+", message = Constants.EXPIRATION_MONTH_FORMAT )
	private String expirationMonth;
	
	@NotNull(message = Constants.EXPIRATION_YEAR_NOT_NULL)
	@Length(max = 2, min = 2, message = Constants.EXPIRATION_YEAR_LENGTH)
	@Pattern(regexp = "\\d+", message = Constants.EXPIRATION_YEAR_FORMAT)
	private String expirationYear;
	
	@NotNull(message = Constants.CVV_NOT_NULL)
	@Length(max = 4, min = 3, message = Constants.CVV_LENGTH)
	private String cvv;
}
