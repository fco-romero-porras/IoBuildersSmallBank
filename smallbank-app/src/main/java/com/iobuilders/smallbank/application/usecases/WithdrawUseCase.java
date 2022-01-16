package com.iobuilders.smallbank.application.usecases;

public interface WithdrawUseCase {

	boolean withdraw(String username, Double amount);
}
