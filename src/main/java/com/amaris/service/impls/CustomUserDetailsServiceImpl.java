package com.amaris.service.impls;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.Account;
import com.amaris.domain.AccountRoleMap;
import com.amaris.exception.impl.NotFoundException;
import com.amaris.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomUserDetailsServiceImpl(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);

        if (account == null) {
            throw new NotFoundException(GlobalConstants.INVALID_USER);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            for (AccountRoleMap accountRoleMap : account.getAccountRoleMaps()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(accountRoleMap.getRole().getName()));
            }

            return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
        }
    }
}
