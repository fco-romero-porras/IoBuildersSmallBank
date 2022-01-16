package com.iobuilders.smallbank.application.usecases;

import com.iobuilders.smallbank.domain.model.user.UserDTO;

public interface RegisterUserUseCase {
	
	UserDTO registerUser(String username, String password);
}
