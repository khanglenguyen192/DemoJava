package com.amaris.service;

import com.amaris.domain.Account;
import com.amaris.domain.AccountRoleMap;
import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    boolean createNewAccount(CreateAccountDto request);

    boolean updateAccount(UpdateAccountDto request);

    boolean verifyAccount(String email, String password);

    List<AccountRoleMap> getAccountRoles(Integer accountId);
}
