package com.trainingsv2.web.rest;

import com.trainingsv2.common.exception.impl.NotFoundException;
import com.trainingsv2.common.utils.ApiConstants;
import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.config.SecurityConfig;
import com.trainingsv2.domain.Cart;
import com.trainingsv2.domain.ItemSale;
import com.trainingsv2.dto.item.ItemDto;
import com.trainingsv2.repository.CartRepository;
import com.trainingsv2.service.mapper.ItemSaleMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartRepository cartRepository;

    private final RestTemplate restTemplate;

    private final ItemSaleMapper itemSaleMapper;

    public CartController(CartRepository cartRepository, RestTemplate restTemplate, ItemSaleMapper itemSaleMapper) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
        this.itemSaleMapper = itemSaleMapper;
    }

    @PostMapping("create")
    public ResponseEntity<String> createCart(@RequestBody Cart request) {

        cartRepository.save(request);

        return ResponseEntity.ok(GlobalConstants.SUCCESS);
    }

    @GetMapping("addItem")
    public ResponseEntity<String> addItemToCart(@RequestParam(name = "itemId") Integer itemId,
                                                @RequestParam(name = "cartId") Integer cartId,
                                                @RequestParam(name = "quantity", defaultValue = "1") int quantity) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(SecurityConfig.JWT_TOKEN);

        HttpEntity<String> httpRequest = new HttpEntity<>(headers);

        String api = ApiConstants.ITEMS + "?id=" + itemId.toString();

        ResponseEntity<ItemDto> response = restTemplate.exchange(api, HttpMethod.GET, httpRequest, ItemDto.class);

        ItemDto itemDto = response.getBody();

        ItemSale itemSale = itemSaleMapper.toItemSale(itemDto);
        itemSale.setQuantity(quantity);

        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        cart.addItemSale(itemSale);

        cartRepository.save(cart);

        return ResponseEntity.ok(GlobalConstants.SUCCESS);
    }
}