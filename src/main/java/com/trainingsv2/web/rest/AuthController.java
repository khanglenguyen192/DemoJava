package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.ApiConstants;
import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.dto.account.LoginDto;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final RestTemplate restTemplate;

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto request) {
        try {
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            var loginBody = new JSONObject();
            loginBody.put("email", request.getEmail());
            loginBody.put("password", request.getPassword());

            HttpEntity<String> httpRequest = new HttpEntity<>(loginBody.toString(), headers);
            String value = restTemplate.postForObject(ApiConstants.LOGIN, httpRequest, String.class);
            return ResponseEntity.ok(value);

        } catch (BadCredentialsException ex) {
            log.error(GlobalConstants.INVALID_USER);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
