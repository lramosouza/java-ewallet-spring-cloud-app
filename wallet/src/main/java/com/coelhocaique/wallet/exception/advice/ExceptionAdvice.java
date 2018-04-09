package com.lsouza.wallet.exception.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsouza.wallet.dto.BaseDTO;
import com.lsouza.wallet.exception.WalletException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(Exception ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(WalletException.class)
	public ResponseEntity<BaseDTO> processParameterizedValidationError(WalletException ex) {
		log.error(ex.getMessage(),ex);
		return processError(ex.getMessage(),ex.getStatusCode());
	}
	
	@ResponseBody
	@ExceptionHandler(HystrixRuntimeException.class)
	public ResponseEntity<Object> processParameterizedValidationError(HystrixRuntimeException ex) {
		log.error(ex.getMessage(),ex);
		if(ex.getCause() instanceof FeignException){
			FeignException fe = (FeignException) ex.getCause();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<>(fe.getMessage(),httpHeaders,HttpStatus.valueOf(fe.status()));
		}
		return null;
	}

	private ResponseEntity<BaseDTO> processError(String error,HttpStatus headerStatus) {
		BaseDTO baseDTO = new BaseDTO(headerStatus.value(),error);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(baseDTO,httpHeaders,headerStatus);
	}
}