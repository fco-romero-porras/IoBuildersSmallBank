package com.iobuilders.smallbank.port;

import com.iobuilders.smallbank.domain.model.user.WalletAccountDTO;

public interface IobWalletPort {
	
	final String iobWalletAddress = "0x5b1869D9A4C187F2EAa108f3062412ecf0526b24";
	
	boolean buyTokens(WalletAccountDTO account, Double amount);
	
	boolean withdraw(WalletAccountDTO account, Double amount);
	
	boolean transfer(WalletAccountDTO sender, Double amount, WalletAccountDTO receiver);
}
