package com.iobuilders.smallbank.infrastructure.db.springdata.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private WalletAccountEntity walletAccount;
	
	public UserEntity() {}

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

	public WalletAccountEntity getWalletAccount() {
		return walletAccount;
	}

	public void setWalletAccount(WalletAccountEntity walletAccount) {
		this.walletAccount = walletAccount;
	}
}
