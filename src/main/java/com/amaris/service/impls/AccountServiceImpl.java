package com.amaris.service.impls;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.config.SecurityConfig;
import com.amaris.domain.Account;
import com.amaris.domain.UserRole;
import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import com.amaris.exception.impl.NotAllowException;
import com.amaris.exception.impl.NotFoundException;
import com.amaris.repository.AccountRepository;
import com.amaris.repository.UserRoleRepository;
import com.amaris.service.AccountService;
import com.amaris.service.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final SecurityConfig securityConfig;
    private final AccountRepository accountRepository;
    private final UserRoleRepository userRoleRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(SecurityConfig securityConfig, AccountRepository accountRepository, UserRoleRepository userRoleRepository, AccountMapper accountMapper) {
        this.securityConfig = securityConfig;
        this.accountRepository = accountRepository;
        this.userRoleRepository = userRoleRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public boolean createNewAccount(CreateAccountDto request) {

        request.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));

        Account newAccount = accountMapper.toAccount(request);

        newAccount.setCreatedBy("khang");
        newAccount.setCreatedDate(LocalDateTime.now());

        Account account = accountRepository.findByEmail(request.getEmail());
        if (account != null) throw new NotAllowException(GlobalConstants.EMIAL_EXISTS);

        List<UserRole> roles = userRoleRepository.findAllById(request.getRoleIds());
        newAccount.setRoles(roles);

        return accountRepository.save(newAccount) != null;
    }

    @Override
    public boolean updateAccount(UpdateAccountDto updateAccountDto) {
        Account account = accountRepository.findByEmail(updateAccountDto.getEmail());

        if (account == null) throw new NotFoundException(GlobalConstants.EMAIL_NOT_FOUND);

        account.setModifyDate(LocalDateTime.now());
        accountMapper.toAccount(account, updateAccountDto);

        return accountRepository.save(account) != null;
    }

    @Override
    public boolean verifyAccount(String email, String password) {
        for (Account account : accountRepository.findAll()) {
            if (email.equals(account.getEmail()) && securityConfig.passwordEncoder().matches(password, account.getPassword()))
                return true;
        }

        return false;
    }
}
