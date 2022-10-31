package com.trainingsv2.service.mapper;

import com.trainingsv2.domain.Account;
import com.trainingsv2.dto.account.CreateAccountDto;
import com.trainingsv2.dto.account.UpdateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(CreateAccountDto createAccountDto);

    Account toAccount(UpdateAccountDto updateAccountDto);

    Account toAccount(@MappingTarget Account account, UpdateAccountDto updateAccountDto);
}
