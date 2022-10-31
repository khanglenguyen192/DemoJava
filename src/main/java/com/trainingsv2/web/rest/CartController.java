package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.ApiConstants;
import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.Cart;
import com.trainingsv2.repository.CartRepository;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping("create")
    public ResponseEntity<String> createCart(@RequestBody Cart request) {

        cartRepository.save(request);

        return ResponseEntity.ok(GlobalConstants.SUCCESS);
    }

    @PostMapping("addItem")
    public ResponseEntity<String> addItemToCart(@RequestParam(name = "itemId") Integer itemId,
                                                @RequestParam(name = "quantity", defaultValue = "1") int quantity) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var loginBody = new JSONObject();
//        loginBody.put("email", request.getEmail());
//        loginBody.put("password", request.getPassword());
//
//        HttpEntity<String> httpRequest = new HttpEntity<>(loginBody.toString(), headers);
//        String value = restTemplate.postForObject(ApiConstants.LOGIN, httpRequest, String.class);
//        return ResponseEntity.ok(value);

        return ResponseEntity.ok(GlobalConstants.SUCCESS);
    }
}
