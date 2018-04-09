package com.lsouza.wallet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lsouza.wallet.consts.Constants;
import com.lsouza.wallet.dto.WalletRequestDTO;
import com.lsouza.wallet.dto.WalletResponseDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.model.Wallet;
import com.lsouza.wallet.parser.WalletParser;
import com.lsouza.wallet.repository.WalletRepository;
import com.lsouza.wallet.service.WalletService;
import com.lsouza.wallet.utils.WalletUtils;
import com.lsouza.wallet.validation.WalletValidation;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public WalletResponseDTO create(WalletRequestDTO walletDTO, String userKey) throws WalletException{
		WalletValidation.validateWalletRequest(walletDTO);
		return Optional.of(walletDTO)
						.map(e -> WalletParser.toEntity(e, userKey))
						.map(walletRepository::save)
						.map(WalletParser::toDTO)
						.get();
	}
	
	@Override
	public WalletResponseDTO find(String id, String userKey) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		return walletRepository.findByIdAndUserId(id, userId)
								.map(WalletParser::toDTO)
								.orElseThrow(() -> new WalletException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}

	@Override
	public List<WalletResponseDTO> find(String userKey) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		return walletRepository.findByUserId(userId)
								.map(WalletParser::toDTOs)
								.orElseThrow(() -> new WalletException(HttpStatus.NOT_FOUND, Constants.WALLET_EMPTY));
	}

	@Override
	public WalletResponseDTO delete(String id, String userKey) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		Optional<Wallet> wallet = walletRepository.findByIdAndUserId(id, userId);
		
		wallet.ifPresent(walletRepository::delete);
		
		return wallet.map(WalletParser::toDTO)
					 .orElseThrow(() -> new WalletException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}

}
