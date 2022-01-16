package com.iobuilders.smallbank.application.usecases;

import com.iobuilders.smallbank.domain.model.user.UserDTO;

public interface GetUserByUsernameUseCase {

	UserDTO getUserByUsername(String username);
}
