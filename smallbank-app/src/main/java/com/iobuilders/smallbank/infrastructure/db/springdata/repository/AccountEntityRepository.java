package com.iobuilders.smallbank.infrastructure.db.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iobuilders.smallbank.infrastructure.db.springdata.entity.WalletAccountEntity;

@Repository
public interface AccountEntityRepository extends JpaRepository<WalletAccountEntity, Long> {
	
}