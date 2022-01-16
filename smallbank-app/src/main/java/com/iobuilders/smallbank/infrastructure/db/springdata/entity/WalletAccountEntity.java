package com.iobuilders.smallbank.infrastructure.db.springdata.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WalletAccounts")
public class WalletAccountEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String walletName;
	
	private String password;
	
	private Double ethBalance;
	
	private Double tokenBalance;
	
	public WalletAccountEntity() {}
	
	public WalletAccountEntity(String walletName, String password) {
		this.walletName = walletName;
		this.password = password;
		this.ethBalance = 0d;
		this.tokenBalance = 0d;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getEthBalance() {
		return ethBalance;
	}

	public void setEthBalance(Double ethBalance) {
		this.ethBalance = ethBalance;
	}

	public Double getTokenBalance() {
		return tokenBalance;
	}

	public void setTokenBalance(Double tokenBalance) {
		this.tokenBalance = tokenBalance;
	}
}
