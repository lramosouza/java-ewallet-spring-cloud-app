package com.lsouza.wallet.resource;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.TokenDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.service.TokenService;

@RequestMapping("/v1/ewallet/{walletId}/token")
@RestController
public class TokenResource {
	
	@Autowired
	private TokenService tokenService;
	
	@PutMapping
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> tokenize(@PathVariable("walletId") String walletId,
											 @RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		TokenDTO tokenDTO = tokenService.tokenize(userKey, walletId);
		
		return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.CREATED);
	}
	
	@GetMapping
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> find(@PathVariable("walletId") String walletId,
											  	  @RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		TokenDTO tokenDTO = tokenService.find(walletId, userKey);
		
		return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.OK);
	}
	
	@DeleteMapping
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> delete(@PathVariable("walletId") String walletId,
													@RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		TokenDTO tokenDTO = tokenService.delete(walletId, userKey);
		
		return new ResponseEntity<TokenDTO>(tokenDTO, HttpStatus.OK);
	}
}
