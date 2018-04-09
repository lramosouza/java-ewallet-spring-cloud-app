package com.lsouza.wallet.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.WalletRequestDTO;
import com.lsouza.wallet.dto.WalletResponseDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.service.WalletService;

@RequestMapping("/v1/ewallet")
@RestController
public class WalletResource {
	
	@Autowired
	private WalletService walletService;
	
	@PostMapping
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletResponseDTO> create(@RequestBody @Valid WalletRequestDTO walletDTO,
											@RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		WalletResponseDTO walletResponseDTO = walletService.create(walletDTO, userKey);
		
		return new ResponseEntity<WalletResponseDTO>(walletResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletResponseDTO> find(@PathVariable("id") String id,
											  	  @RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		WalletResponseDTO walletResponseDTO = walletService.find(id, userKey);
		
		return new ResponseEntity<WalletResponseDTO>(walletResponseDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WalletResponseDTO>> find(@RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		List<WalletResponseDTO> walletDTOs = walletService.find(userKey);
		
		return new ResponseEntity<List<WalletResponseDTO>>(walletDTOs,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WalletResponseDTO> delete(@PathVariable("id") String id,
													@RequestHeader(value = Constants.AUTHORIZATION) String userKey) throws WalletException{
		
		WalletResponseDTO walletResponseDTO = walletService.delete(id, userKey);
		
		return new ResponseEntity<WalletResponseDTO>(walletResponseDTO, HttpStatus.OK);
	}
}
