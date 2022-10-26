package com.amaris.service.mapper;

import com.amaris.domain.Account;
import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    public Account toAccount(CreateAccountDto createAccountDto);

    public Account toAccount(UpdateAccountDto updateAccountDto);

    public Account toAccount(@MappingTarget Account account, UpdateAccountDto updateAccountDto);
}
