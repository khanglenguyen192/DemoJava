package com.amaris.web.rest;

import com.amaris.common.jwt.JwtTokenUtils;
import com.amaris.common.utils.GlobalConstants;
import com.amaris.dto.account.LoginDto;
import com.amaris.service.impls.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AccountServiceImpl accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtil;

    public AuthController(AccountServiceImpl accountService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtil) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    @Around("execution(* com.amaris.web.rest..*.*(..))")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto request) {
        try {
            log.info("/api/auth/login");

            if (accountService.verifyAccount(request.getEmail(), request.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                UserDetails account = (UserDetails) authentication.getPrincipal();
                String accessToken = jwtTokenUtil.generateToken(account.getUsername());

                return ResponseEntity.ok(accessToken);
            }

            return new ResponseEntity<>(GlobalConstants.LOGIN_FAILED, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException ex) {
            log.error(GlobalConstants.INVALID_USER);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
