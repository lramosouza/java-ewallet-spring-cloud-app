package com.lsouza.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lsouza.user.consts.Constants;
import com.lsouza.user.dto.UserDTO;
import com.lsouza.user.exception.UserException;
import com.lsouza.user.model.User;
import com.lsouza.user.parser.UserParser;
import com.lsouza.user.repository.UserRepository;
import com.lsouza.user.service.UserService;
import com.lsouza.user.utils.UserUtils;

import lombok.Setter;

@Service
public class UserServiceImpl implements UserService {
	
	@Setter
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO create(UserDTO userDTO) {
		return Optional.ofNullable(userDTO)
						.map(UserParser::toEntity)
						.map(userRepository::save)
						.map(UserUtils::generateKey)
						.map(userRepository::save)
						.map(UserParser::toDTO)
						.get();
	}
	
	@Override
	public UserDTO delete(String key) throws UserException {
		String[] decodedKey = UserUtils.getValidDecodedKey(key);
		Optional<User> user = userRepository.findByIdAndUsername(decodedKey[0], decodedKey[1]);
											
		user.ifPresent(userRepository::delete);
							  
		return user.map(UserParser::toDTO)
					.orElse(UserParser.toDTO(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND));
	}

	@Override
	public UserDTO find(String key) throws UserException {
		String[] decodedKey = UserUtils.getValidDecodedKey(key);
		
		return userRepository.findByIdAndUsername(decodedKey[0], decodedKey[1])
						.map(UserParser::toDTO)
						.orElse(UserParser.toDTO(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND));
	}
	
	@Override
	public List<UserDTO> findAll() throws UserException {
		return Optional.ofNullable(userRepository.findAll())
						.map(UserParser::toDTOs)
						.orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND.value(), Constants.USER_NOT_FOUND));
	}

	@Override
	public UserDTO authenticate(String key) throws UserException {
		String[] decodedKey = UserUtils.getValidDecodedKey(key);
		
		return userRepository.findByIdAndUsername(decodedKey[0], decodedKey[1])
							.map(UserParser::toDTO)
							.orElse(UserParser.toDTO(HttpStatus.UNAUTHORIZED, Constants.INVALID_KEY));
	}
}
