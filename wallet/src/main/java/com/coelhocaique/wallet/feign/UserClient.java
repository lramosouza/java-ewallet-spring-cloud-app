package com.lsouza.wallet.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.BaseDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.feign.fallbacks.UserClientFallback;

@FeignClient(name="user",fallback = UserClientFallback.class)
public interface UserClient {
	
	@RequestMapping(value = "/v1/authenticate",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseDTO> authenticate(@RequestHeader(name = Constants.AUTHORIZATION) String key) throws WalletException;
}
