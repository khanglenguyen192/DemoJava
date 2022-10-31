package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.dto.account.CreateAccountDto;
import com.trainingsv2.dto.account.UpdateAccountDto;
import com.trainingsv2.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<String> createAccount(@RequestBody @Valid CreateAccountDto request) {
        if (accountService.createNewAccount(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateAccount(@RequestBody UpdateAccountDto request) {
        if (accountService.updateAccount(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }
}
