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
public class TransactionController {

	@Autowired
	private RegisterUserUseCase registerUserUseCase;

	@Autowired
	private GetAllUsersUseCase getAllUsersUseCase;

	@Autowired
	private GetUserByUsernameUseCase getUserByUsernameUseCase;

	@PostMapping()
	public void registerUser(@RequestParam String username, @RequestParam String password) {
		registerUserUseCase.registerUser(username, password);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = getAllUsersUseCase.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
		UserDTO user = getUserByUsernameUseCase.getUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}