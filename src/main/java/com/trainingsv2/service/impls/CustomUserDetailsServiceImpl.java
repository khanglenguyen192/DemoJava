package com.trainingsv2.service.impls;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.Account;
import com.trainingsv2.common.exception.impl.NotFoundException;
import com.trainingsv2.repository.AccountRepository;
import com.trainingsv2.repository.AccountRoleMapRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    private final AccountRoleMapRepository accountRoleMapRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomUserDetailsServiceImpl(AccountRepository accountRepository, AccountRoleMapRepository accountRoleMapRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.accountRoleMapRepository = accountRoleMapRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);

        if (account == null) {
            throw new NotFoundException(GlobalConstants.INVALID_USER);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//            for (AccountRoleMap accountRoleMap : accountRoleMapRepository.findAllByAccountId(account.getAccountId())) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(accountRoleMap.getRole().getName()));
//            }

            return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
        }
    }
}
