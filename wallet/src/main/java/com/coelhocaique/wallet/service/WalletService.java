package com.lsouza.wallet.service;

import java.util.List;

import com.lsouza.wallet.dto.WalletRequestDTO;
import com.lsouza.wallet.dto.WalletResponseDTO;
import com.lsouza.wallet.exception.WalletException;

public interface WalletService {
	
	WalletResponseDTO create(WalletRequestDTO walletDTO, String userKey) throws WalletException;
	
	WalletResponseDTO find(String id, String userKey) throws WalletException;
	
	List<WalletResponseDTO> find(String userKey) throws WalletException;
	
	WalletResponseDTO delete(String id, String userKey) throws WalletException;
}
