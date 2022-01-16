package com.iobuilders.smallbank.port;

import com.iobuilders.smallbank.domain.model.user.WalletAccountDTO;

public interface IobTokenPort {
	
	final String iobTokenAddress = "0xe78A0F7E598Cc8b0Bb87894B0F60dD2a88d6a8Ab";
	
	final String faucetAddress = "0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1";
	
	Double getTokenBalance(WalletAccountDTO account);
	
	Double getEthBalance(WalletAccountDTO account);

	boolean deposit(WalletAccountDTO account, Double amount);
}
