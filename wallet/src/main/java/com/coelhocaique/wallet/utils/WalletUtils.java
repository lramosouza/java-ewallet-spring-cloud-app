package com.lsouza.wallet.utils;

import org.springframework.security.crypto.codec.Base64;

public class WalletUtils {
	
	public static String[] decodeKey(String key){
		String decodedKey = decodeBase64(key);
		return decodedKey.split(":");
	}
	
	public static String decodeBase64(String content){
		return new String(Base64.decode(content.getBytes()));
	}
}
