package com.lsouza.wallet.service;

import com.lsouza.wallet.dto.TokenDTO;
import com.lsouza.wallet.exception.WalletException;

public interface TokenService {
		
	TokenDTO tokenize(String userKey,String walletId) throws WalletException;

	TokenDTO find(String walletId, String userKey) throws WalletException;

	TokenDTO delete(String walletId, String userKey) throws WalletException;
}
