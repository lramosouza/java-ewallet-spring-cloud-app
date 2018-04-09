package com.lsouza.wallet.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lsouza.wallet.model.Token;

public interface TokenRepository extends MongoRepository<Token, String>{
	
	Optional<Token> findByWalletId(String walletId);
}