package com.trainingsv2.service.impls;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.config.SecurityConfig;
import com.trainingsv2.domain.Account;
import com.trainingsv2.domain.AccountRoleMap;
import com.trainingsv2.domain.UserRole;
import com.trainingsv2.dto.account.CreateAccountDto;
import com.trainingsv2.dto.account.UpdateAccountDto;
import com.trainingsv2.common.exception.impl.NotAllowException;
import com.trainingsv2.common.exception.impl.NotFoundException;
import com.trainingsv2.repository.AccountRepository;
import com.trainingsv2.repository.AccountRoleMapRepository;
import com.trainingsv2.repository.UserRoleRepository;
import com.trainingsv2.service.AccountService;
import com.trainingsv2.service.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final SecurityConfig securityConfig;
    private final AccountRepository accountRepository;
    private final UserRoleRepository userRoleRepository;

    private final AccountRoleMapRepository accountRoleMapRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(SecurityConfig securityConfig, AccountRepository accountRepository, UserRoleRepository userRoleRepository, AccountRoleMapRepository accountRoleMapRepository, AccountMapper accountMapper) {
        this.securityConfig = securityConfig;
        this.accountRepository = accountRepository;
        this.userRoleRepository = userRoleRepository;
        this.accountRoleMapRepository = accountRoleMapRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    @Override
    public boolean createNewAccount(CreateAccountDto request) {

        request.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));

        Account newAccount = accountMapper.toAccount(request);

        newAccount.setCreatedBy("khang");
        newAccount.setCreatedDate(LocalDateTime.now());

        Account account = accountRepository.findByEmail(request.getEmail());
        if (account != null) throw new NotAllowException(GlobalConstants.EMAIL_EXISTS);

        List<UserRole> roles = userRoleRepository.findAllById(request.getRoleIds());

        newAccount = accountRepository.save(newAccount);

        for (UserRole role: roles){
            AccountRoleMap accountRoleMap = new AccountRoleMap();
            accountRoleMap.setAccountId(newAccount.getAccountId());
            accountRoleMap.setRoleId(role.getId());

            accountRoleMapRepository.save(accountRoleMap);
        }

        return true;
    }

    @Override
    public boolean updateAccount(UpdateAccountDto updateAccountDto) {
        Account account = accountRepository.findByEmail(updateAccountDto.getEmail());

        if (account == null) throw new NotFoundException(GlobalConstants.EMAIL_NOT_FOUND);

        account.setModifyDate(LocalDateTime.now());
        accountMapper.toAccount(account, updateAccountDto);

        return accountRepository.update(account) != null;
    }

    @Override
    public boolean verifyAccount(String email, String password) {
        for (Account account : accountRepository.findAll()) {
            if (email.equals(account.getEmail()) && securityConfig.passwordEncoder().matches(password, account.getPassword()))
                return true;
        }

        return false;
    }

    @Override
    public List<AccountRoleMap> getAccountRoles(Integer accountId) {
        return accountRoleMapRepository.findAllByAccountId(accountId);
    }
}
