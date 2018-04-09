package com.lsouza.wallet.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.WalletRequestDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.model.Wallet;

public class WalletValidation {
	
	public static void validateWalletRequest(WalletRequestDTO walletDTO) throws WalletException{
		creditCardExpired(walletDTO);
	}

	private static void creditCardExpired(WalletRequestDTO walletDTO) throws WalletException {
		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();
		int year = Integer.parseInt(String.valueOf(currentDate.getYear()).substring(1));		
		int expireMonth = Integer.parseInt(walletDTO.getExpirationMonth());
		int expireYear = Integer.parseInt(walletDTO.getExpirationYear());
		
		if(expireYear < year || (expireYear == year && expireMonth < month)){
			throw new WalletException(HttpStatus.BAD_REQUEST,Constants.CREDITCARD_EXPIRED);
		}
	}
	
	public static void validateTokenization(Optional<Wallet> optional) throws WalletException {
		validateNull(optional);
		optional.filter(w -> !w.isTokenized())
				.orElseThrow(() -> new WalletException(HttpStatus.BAD_REQUEST, Constants.ALREADY_TOKENIZED));
	}
	
	public static void validateWalletToken(Optional<Wallet> optional) throws WalletException {
		validateNull(optional);
		optional.filter(w -> w.isTokenized())
				.orElseThrow(() -> new WalletException(HttpStatus.NOT_FOUND, Constants.NOT_TOKENIZED));
	}
	
	
	private static void validateNull(Optional<Wallet> optional) throws WalletException {
		optional.orElseThrow(() -> new WalletException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
