package com.iobuilders.smallbank.application.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import com.iobuilders.smallbank.application.usecases.GetAllUsersUseCase;
import com.iobuilders.smallbank.application.usecases.GetUserByUsernameUseCase;
import com.iobuilders.smallbank.application.usecases.RegisterUserUseCase;
import com.iobuilders.smallbank.domain.model.user.WalletAccountDTO;
import com.iobuilders.smallbank.domain.model.user.UserDTO;
import com.iobuilders.smallbank.port.LoadUserPort;
import com.iobuilders.smallbank.port.PersistUserPort;

@Service
public class UserService implements RegisterUserUseCase, GetAllUsersUseCase, GetUserByUsernameUseCase {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PersistUserPort persistUserPort;

	@Autowired
	private LoadUserPort loadUserPort;

	@Override
	public UserDTO registerUser(String username, String password) {
		WalletAccountDTO account = createWalletAccount(password);
		UserDTO user = new UserDTO(username, password, account);
		persistUserPort.saveUser(user);
		return user;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> users = loadUserPort.loadUsers();
		return users;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		return user;
	}

	private WalletAccountDTO createWalletAccount(String password) {
		WalletAccountDTO accountDTO = null;
		String credentialsPath = "src/main/resources/credentials";
		try {
			String walletName = WalletUtils.generateNewWalletFile(password, new File(credentialsPath), false);
			accountDTO = new WalletAccountDTO(credentialsPath + "/" + walletName, password);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return accountDTO;
	}
}
