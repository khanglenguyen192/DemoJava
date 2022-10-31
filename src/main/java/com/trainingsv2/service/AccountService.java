package com.trainingsv2.service;

import com.trainingsv2.domain.AccountRoleMap;
import com.trainingsv2.dto.account.CreateAccountDto;
import com.trainingsv2.dto.account.UpdateAccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    boolean createNewAccount(CreateAccountDto request);

    boolean updateAccount(UpdateAccountDto request);

    boolean verifyAccount(String email, String password);

    List<AccountRoleMap> getAccountRoles(Integer accountId);
}
