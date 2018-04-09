package com.lsouza.user.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.codec.Base64;

import com.lsouza.user.consts.Constants;
import com.lsouza.user.exception.UserException;
import com.lsouza.user.model.User;

public class UserUtils {
	
	public static User generateKey(User user){
		String key = user.getId() + ":" + user.getUsername();
		user.setKey(encodeBase64(key));
		return user;
	}
	
	public static String encodeBase64(String content){
		return new String(Base64.encode(content.getBytes()));
	}
	
	public static String[] decodeKey(String key){
		String decodedKey = decodeBase64(key);
		return decodedKey.split(":");
	}
	
	public static String decodeBase64(String content){
		return new String(Base64.decode(content.getBytes()));
	}
	
	public static String[] getValidDecodedKey(String userKey) throws UserException{
		String[] decodedKey = decodeKey(userKey);
		
		if(decodedKey.length < 2){
			throw new UserException(HttpStatus.UNAUTHORIZED.value(), Constants.INVALID_KEY);
		}
		
		return decodedKey;
	}
}
