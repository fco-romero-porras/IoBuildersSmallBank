package com.iobuilders.smallbank.infrastructure.db.springdata.mapper;

import org.mapstruct.Mapper;

import com.iobuilders.smallbank.domain.model.user.WalletAccountDTO;
import com.iobuilders.smallbank.infrastructure.db.springdata.entity.WalletAccountEntity;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

	WalletAccountDTO toDomain(WalletAccountEntity walletAccountEntity);

	WalletAccountEntity toDbo(WalletAccountDTO accountDTO);

}