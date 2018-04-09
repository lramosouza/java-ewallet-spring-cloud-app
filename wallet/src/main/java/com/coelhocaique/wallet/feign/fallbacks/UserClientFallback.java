package com.lsouza.wallet.feign.fallbacks;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.BaseDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.feign.UserClient;

@Component
public class UserClientFallback implements UserClient {

	@Override
	public ResponseEntity<BaseDTO> authenticate(String key) throws WalletException {
		HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
		return new ResponseEntity<>(new BaseDTO(unauthorized.value(), Constants.SERVER_ERROR),unauthorized);
	}
}
