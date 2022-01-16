package com.iobuilders.smallbank.infrastructure.db.springdata.mapper;

import org.mapstruct.Mapper;

import com.iobuilders.smallbank.domain.model.user.UserDTO;
import com.iobuilders.smallbank.infrastructure.db.springdata.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

	UserDTO toDomain(UserEntity userEntity);

	UserEntity toDbo(UserDTO userDTO);

}