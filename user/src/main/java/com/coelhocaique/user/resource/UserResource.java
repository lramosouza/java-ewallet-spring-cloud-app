package com.lsouza.user.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

import com.lsouza.user.consts.Constants;
import com.lsouza.user.dto.UserDTO;
import com.lsouza.user.exception.UserException;
import com.lsouza.user.service.UserService;

@RequestMapping("/v1")
@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO){
		
		userDTO = userService.create(userDTO);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/{key}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> get(@PathVariable("key") String key) throws UserException{
		
		UserDTO userDTO = userService.find(key);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
	
	@GetMapping
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> get() throws UserException{
		
		List<UserDTO> userDTOs = userService.findAll();
		
		return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/authenticate")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> authenticate(@RequestHeader(name = Constants.AUTHORIZATION) @NotNull String key) throws UserException{
		
		UserDTO userDTO = userService.authenticate(key);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
	
	@DeleteMapping("/{key}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> delete(@PathVariable("key") String key) throws UserException{
		
		UserDTO userDTO = userService.delete(key);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
}
