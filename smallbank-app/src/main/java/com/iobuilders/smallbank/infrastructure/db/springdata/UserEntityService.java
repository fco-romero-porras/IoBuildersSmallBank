package com.iobuilders.smallbank.infrastructure.db.springdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iobuilders.smallbank.domain.model.user.UserDTO;
import com.iobuilders.smallbank.infrastructure.db.springdata.entity.UserEntity;
import com.iobuilders.smallbank.infrastructure.db.springdata.mapper.UserEntityMapper;
import com.iobuilders.smallbank.infrastructure.db.springdata.repository.UserEntityRepository;
import com.iobuilders.smallbank.port.LoadUserPort;
import com.iobuilders.smallbank.port.PersistUserPort;

@Service
public class UserEntityService implements PersistUserPort, LoadUserPort{

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private UserEntityMapper userMapper;

	@Override
	public void saveUser(UserDTO user) {
		try {
			UserEntity entity = userMapper.toDbo(user);
			userEntityRepository.saveAndFlush(entity);
		} catch(Exception e) {

		}
	}

	@Override
	public UserDTO loadUserByUsername(String username) {
		UserDTO dto = null;
		try {
			UserEntity entity = userEntityRepository.findByUsername(username);
			dto = userMapper.toDomain(entity);	
		}  catch(Exception e) {

		}
		return dto;
	}

	@Override
	public List<UserDTO> loadUsers(){
		List<UserDTO> dtoList = new ArrayList<>();
		try {
			List<UserEntity> entityList = userEntityRepository.findAll();
			for(UserEntity entity: entityList) {
				UserDTO dto = userMapper.toDomain(entity);
				dtoList.add(dto);
			}
		} catch(Exception e) {

		}

		return dtoList;
	}
}
