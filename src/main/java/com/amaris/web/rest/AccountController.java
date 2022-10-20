package com.amaris.web.rest;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.dto.account.CreateAccountDto;
import com.amaris.dto.account.UpdateAccountDto;
import com.amaris.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("createAccount")
    @Transactional
    public ResponseEntity<String> CreateAccount(@RequestBody @Valid CreateAccountDto request) {
        if (accountService.createNewAccount(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("updateAccount")
    @Transactional
    public ResponseEntity<String> UpdateAccount(@RequestBody UpdateAccountDto request)
    {
        if (accountService.updateAccount(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }
}
