package com.iobuilders.smallbank.application.usecases;

public interface TransferUseCase {

	Boolean transfer(String sender, Double amount, String receiver);
}
