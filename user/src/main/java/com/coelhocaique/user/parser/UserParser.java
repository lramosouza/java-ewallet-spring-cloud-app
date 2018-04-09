package com.lsouza.user.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import com.lsouza.user.dto.UserDTO;
import com.lsouza.user.model.User;

public class UserParser {
	
	public static User toEntity(UserDTO dto){
		User entity = null;
		
		if(dto != null){
			entity = new User();
			BeanUtils.copyProperties(dto, entity);
		}
		
		return entity;
	}
	
	public static UserDTO toDTO(User entity){
		UserDTO dto = null;
		
		if(entity != null){
			dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
		}
		
		return dto;
	}
	
	public static List<UserDTO> toDTOs(List<User> entities){
		List<UserDTO> dtos = null;
		
		if(CollectionUtils.isNotEmpty(entities)){
			dtos = entities.stream()
							.map(UserParser::toDTO)
							.collect(Collectors.toList());
		}
		
		return dtos;
	}
	
	public static UserDTO toDTO(HttpStatus statusCode, String message){
		return UserDTO.builder()
				.code(statusCode.value())
				.returnMessage(message)
				.build();
	}
}
