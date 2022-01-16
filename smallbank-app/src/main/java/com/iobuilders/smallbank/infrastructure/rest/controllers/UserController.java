package com.iobuilders.smallbank.infrastructure.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iobuilders.smallbank.application.usecases.BuyIobTokensUseCase;
import com.iobuilders.smallbank.application.usecases.DepositUseCase;
import com.iobuilders.smallbank.application.usecases.GetAllUsersUseCase;
import com.iobuilders.smallbank.application.usecases.GetUserByUsernameUseCase;
import com.iobuilders.smallbank.application.usecases.RegisterUserUseCase;
import com.iobuilders.smallbank.application.usecases.TransferUseCase;
import com.iobuilders.smallbank.application.usecases.WithdrawUseCase;
import com.iobuilders.smallbank.domain.model.user.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private DepositUseCase depositUseCase;

	@Autowired
	private WithdrawUseCase withdrawUseCase;

	@Autowired
	private TransferUseCase transferUseCase;

	@Autowired
	private BuyIobTokensUseCase buyIobTokensUseCase;


	@PostMapping(value = "{username}/deposit/{amount}")
	public ResponseEntity<String> deposit(@PathVariable String username, @PathVariable Double amount) {
		if (depositUseCase.deposit(username, amount)) {
			return new ResponseEntity<>("Deposit done successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}

	@PostMapping(value = "{username}/buyIobTokens/{amount}")
	public ResponseEntity<String> buyIobTokens(@PathVariable String username, @PathVariable Double amount) {
		if (buyIobTokensUseCase.buyIobTokens(username, amount)) {
			return new ResponseEntity<>(String.format("You bought %f IobTokens", amount), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}

	@PostMapping(value = "{sender}/transfer/{amount}/{receiver}")
	public ResponseEntity<String> transfer(@PathVariable String sender, @PathVariable Double amount,
			@PathVariable String receiver) {
		if (transferUseCase.transfer(sender, amount, receiver)) {
			return new ResponseEntity<>(String.format("%f IOB transfered to %s", amount, receiver), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}

	@PostMapping(value = "{username}/withdraw/{amount}")
	public ResponseEntity<String> withdraw(@PathVariable String username, @PathVariable Double amount) {
		if (withdrawUseCase.withdraw(username, amount)) {
			return new ResponseEntity<>(String.format("%f tokens sold successfully", amount), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}
}