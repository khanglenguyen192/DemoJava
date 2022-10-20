package com.amaris.service.mapper;

import com.amaris.domain.Account;
import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(CreateAccountDto createAccountDto);

    Account toAccount(UpdateAccountDto updateAccountDto);

    Account toAccount(@MappingTarget Account account, UpdateAccountDto updateAccountDto);
}
