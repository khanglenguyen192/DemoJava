package com.amaris.common.jwt;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.Account;
import com.amaris.domain.AccountRoleMap;
import com.amaris.exception.impl.NotFoundException;
import com.amaris.repository.AccountRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtil;
    private final AccountRepository accountRepository;

    public JwtRequestFilter(JwtTokenUtils jwtTokenUtil, AccountRepository accountRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (hasAuthorizationHeader(request)) {
            String accessToken = getAccessToken(request);
            if (jwtTokenUtil.validateAccessToken(accessToken)) {
                setAuthenticationContext(accessToken, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UserDetails userDetails = getUserDetailsFromAccessToken(accessToken);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails getUserDetailsFromAccessToken(String accessToken) {

        String email = jwtTokenUtil.getEmailFromToken(accessToken);

        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new NotFoundException(GlobalConstants.INVALID_USER);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            for (AccountRoleMap accountRoleMap : account.getAccountRoleMaps())
            {
                grantedAuthorities.add(new SimpleGrantedAuthority(accountRoleMap.getRole().getName()));
            }

            return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
        }
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader(GlobalConstants.AUTHORIZATION);
        return !ObjectUtils.isEmpty(header) && header.startsWith(GlobalConstants.BEARER);
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(GlobalConstants.AUTHORIZATION);

        if (header.startsWith(GlobalConstants.BEARER)){
            String token = header.split(" ")[1].trim();
            return token;
        }

        return null;
    }
}
