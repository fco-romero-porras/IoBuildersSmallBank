package com.iobuilders.smallbank.port;

import com.iobuilders.smallbank.domain.model.user.UserDTO;

public interface PersistUserPort {

	void saveUser(UserDTO user);
}
