package com.lsouza.wallet.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.Crypt;
import org.springframework.util.CollectionUtils;

import com.lsouza.wallet.dto.WalletRequestDTO;
import com.lsouza.wallet.dto.WalletResponseDTO;
import com.lsouza.wallet.model.Wallet;
import com.lsouza.wallet.utils.WalletUtils;

public class WalletParser {
	
	public static Wallet toEntity(WalletRequestDTO dto, String userKey){
		Wallet entity = null;
		
		if(dto != null){
			String cardNumber = dto.getCardNumber();
			
			entity = Wallet.builder()
						.cardholder(dto.getCardholder())
						.expirationYear(Integer.valueOf(dto.getExpirationYear()))
						.expirationMonth(Integer.valueOf(dto.getExpirationMonth()))
						.bin(cardNumber.substring(0,6))
						.last4(cardNumber.substring(cardNumber.length() - 4))
						.userId(WalletUtils.decodeKey(userKey)[0])
						.cardNumber(Crypt.crypt(cardNumber))
						.build();
		}
		
		return entity;
	}
	
	public static WalletResponseDTO toDTO(Wallet entity){
		WalletResponseDTO dto = null;
		
		if(entity != null){
			dto = WalletResponseDTO.builder()
								   .id(entity.getId())
								   .bin(entity.getBin())
								   .last4(entity.getLast4())
								   .tokenized(entity.isTokenized())
								   .build();
		}
		
		return dto;
	}
	
	public static List<WalletResponseDTO> toDTOs(List<Wallet> entities){
		List<WalletResponseDTO> dtos = null;
		
		if(!CollectionUtils.isEmpty(entities)){
			dtos = entities.stream()
					.map(WalletParser::toDTO)
					.collect(Collectors.toList());
		}
		
		return dtos;
	}
}
