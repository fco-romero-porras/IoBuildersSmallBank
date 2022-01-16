package com.iobuilders.smallbank.domain.model.user;

public class UserDTO {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private WalletAccountDTO walletAccount;
	
	public UserDTO() {}
	
	public UserDTO(String username, String password, WalletAccountDTO walletAccount) {
		this.username = username;
		this.password = password;
		this.walletAccount = walletAccount;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WalletAccountDTO getWalletAccount() {
		return walletAccount;
	}

	public void setWalletAccount(WalletAccountDTO account) {
		this.walletAccount = account;
	}

}
