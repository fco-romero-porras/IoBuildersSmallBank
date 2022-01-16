package com.iobuilders.smallbank.infrastructure.db.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iobuilders.smallbank.infrastructure.db.springdata.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
}
