package com.trainingsv2.web.rest;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.dto.base.PageResponse;
import com.trainingsv2.dto.item.ItemDto;
import com.trainingsv2.service.impls.ItemServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping("getAllItem")
    public ResponseEntity<PageResponse<ItemDto>> getAll(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                                        @RequestParam(required = false, defaultValue = "3") int pageSize) {
        PageRequest page = PageRequest.of(pageNo, pageSize);
        return new ResponseEntity<>(itemService.getAllItem(page), HttpStatus.OK);
    }

    @PostMapping("createItem")
    public ResponseEntity<String> createItem(@RequestBody @Valid ItemDto request) {
        if (itemService.createItem(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);;

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("updateItem")
    public ResponseEntity<String> updateItem(@RequestBody @Valid ItemDto request) {
        if (itemService.updateItem(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);;

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ItemDto>> findItems(@RequestParam(required = false) Integer itemId,
                                                           @RequestParam(required = false) String itemName,
                                                           @RequestParam(required = false) Integer catalogId,
                                                           @RequestParam(required = false) String catalogName,
                                                           @RequestParam(required = false) String createBy,
                                                           @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                           @RequestParam(required = false, defaultValue = "100") int pageSize) {

        PageRequest page = PageRequest.of(pageNo, pageSize);
        return new ResponseEntity<>(itemService.findItem(itemId, itemName, catalogId, catalogName, createBy, page), HttpStatus.OK);
    }
}
