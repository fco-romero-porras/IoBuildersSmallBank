package com.iobuilders.smallbank.port;

import java.util.List;

import com.iobuilders.smallbank.domain.model.user.UserDTO;

public interface LoadUserPort {
	
	UserDTO loadUserByUsername(String username);

	List<UserDTO> loadUsers();
}
