package com.iobuilders.smallbank.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iobuilders.smallbank.application.usecases.BuyIobTokensUseCase;
import com.iobuilders.smallbank.application.usecases.DepositUseCase;
import com.iobuilders.smallbank.application.usecases.TransferUseCase;
import com.iobuilders.smallbank.application.usecases.WithdrawUseCase;
import com.iobuilders.smallbank.domain.model.user.UserDTO;
import com.iobuilders.smallbank.port.IobTokenPort;
import com.iobuilders.smallbank.port.IobWalletPort;
import com.iobuilders.smallbank.port.LoadUserPort;
import com.iobuilders.smallbank.port.PersistUserPort;

@Service
public class TransacctionService implements BuyIobTokensUseCase, DepositUseCase, WithdrawUseCase, TransferUseCase {
	
	private Logger logger = LoggerFactory.getLogger(TransacctionService.class);

	@Autowired
	private PersistUserPort persistUserPort;

	@Autowired
	private LoadUserPort loadUserPort;
	
	@Autowired
	private IobWalletPort iobWalletPort;
	
	@Autowired
	private IobTokenPort iobTokenPort;


	@Override
	public Boolean deposit(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			if(iobTokenPort.deposit(user.getWalletAccount(), amount)) {
				user.getWalletAccount().setEthBalance(iobTokenPort.getEthBalance(user.getWalletAccount()));
				persistUserPort.saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean buyIobTokens(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			if(iobWalletPort.buyTokens(user.getWalletAccount(), amount)) {
				user.getWalletAccount().setEthBalance(iobTokenPort.getEthBalance(user.getWalletAccount()));
				user.getWalletAccount().setTokenBalance(iobTokenPort.getTokenBalance(user.getWalletAccount()));
				persistUserPort.saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean transfer(String sender, Double amount, String receiver) {
		UserDTO senderDTO = loadUserPort.loadUserByUsername(sender);
		UserDTO receiverDTO = loadUserPort.loadUserByUsername(receiver);
		if(senderDTO != null && receiverDTO != null) {
			Double senderBalance = senderDTO.getWalletAccount().getTokenBalance();
			if(amount <= senderBalance) {
				if(iobWalletPort.transfer(senderDTO.getWalletAccount(), amount, receiverDTO.getWalletAccount())) {
					
					senderDTO.getWalletAccount().setTokenBalance(iobTokenPort.getTokenBalance(senderDTO.getWalletAccount()));
					senderDTO.getWalletAccount().setEthBalance(iobTokenPort.getEthBalance(senderDTO.getWalletAccount()));
					
					receiverDTO.getWalletAccount().setTokenBalance(iobTokenPort.getTokenBalance(receiverDTO.getWalletAccount()));
					receiverDTO.getWalletAccount().setEthBalance(iobTokenPort.getEthBalance(receiverDTO.getWalletAccount()));
					
					persistUserPort.saveUser(senderDTO);
					persistUserPort.saveUser(receiverDTO);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean withdraw(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			Double actualBalance = user.getWalletAccount().getTokenBalance();
			if(amount <= actualBalance) {
				if(iobWalletPort.withdraw(user.getWalletAccount(), amount)) {
					user.getWalletAccount().setTokenBalance(iobTokenPort.getTokenBalance(user.getWalletAccount()));
					user.getWalletAccount().setEthBalance(iobTokenPort.getEthBalance(user.getWalletAccount()));
					persistUserPort.saveUser(user);
					return true;
				}
			}
		}
		return false;
	}
}
