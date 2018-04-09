package com.lsouza.wallet.service.impl;

import java.util.Optional;

import org.apache.commons.codec.digest.Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import com.lsouza.wallet.dto.TokenDTO;
import com.lsouza.wallet.exception.WalletException;
import com.lsouza.wallet.model.Token;
import com.lsouza.wallet.model.Wallet;
import com.lsouza.wallet.parser.TokenParser;
import com.lsouza.wallet.repository.TokenRepository;
import com.lsouza.wallet.repository.WalletRepository;
import com.lsouza.wallet.service.TokenService;
import com.lsouza.wallet.utils.WalletUtils;
import com.lsouza.wallet.validation.WalletValidation;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public TokenDTO tokenize(String userKey, String walletId) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		
		Optional<Wallet> optionalWallet = findWallet(walletId, userId);
		WalletValidation.validateTokenization(optionalWallet);
		
		Token token = TokenParser.toEntity(walletId);
		tokenRepository.save(token);
		token.setToken(generateToken(token.getId()));
		tokenRepository.save(token);
		
		Wallet wallet = optionalWallet.get();
		wallet.setTokenized(true);
		walletRepository.save(wallet);
		
		return TokenParser.toDTO(token);
	}


	@Override
	public TokenDTO find(String walletId, String userKey) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		WalletValidation.validateWalletToken(findWallet(walletId, userId));
		
		return tokenRepository.findByWalletId(walletId)
							  .map(TokenParser::toDTO)
							  .get();
	}

	@Override
	public TokenDTO delete(String walletId, String userKey) throws WalletException {
		String userId = WalletUtils.decodeKey(userKey)[0];
		Optional<Wallet> optional = findWallet(walletId, userId);
		WalletValidation.validateWalletToken(optional);
		Optional<Token> token = tokenRepository.findByWalletId(walletId);
		
		token.ifPresent(tokenRepository::delete);
		
		Wallet wallet = optional.get();
		wallet.setTokenized(false);
		walletRepository.save(wallet);
		
		return token.map(TokenParser::toDTO).get();
	}

	private Optional<Wallet> findWallet(String walletId, String userId) {
		return walletRepository.findByIdAndUserId(walletId, userId);
	}
	
	private String generateToken(String tokenId) {
		return Optional.ofNullable(tokenId)
				.map(s -> Hex.encode(s.getBytes()).toString())
				.map(Crypt::crypt)
				.orElseThrow(RuntimeException::new);
	}
}
