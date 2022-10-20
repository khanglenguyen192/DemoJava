package com.amaris.service;

import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    boolean createNewAccount(CreateAccountDto request);

    boolean updateAccount(UpdateAccountDto request);

    boolean verifyAccount(String email, String password);
}
