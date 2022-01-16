package com.iobuilders.smallbank.application.usecases;

import java.util.List;

import com.iobuilders.smallbank.domain.model.user.UserDTO;

public interface GetAllUsersUseCase {

	List<UserDTO> getAllUsers();
}
