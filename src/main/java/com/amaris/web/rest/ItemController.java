package com.amaris.web.rest;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.dto.item.ItemDto;
import com.amaris.service.impls.ItemServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping("getAllItem")
    public ResponseEntity<List<ItemDto>> getAll(@PathVariable int pageNo, int pageSize)
    {
        Pageable page = PageRequest.of(pageNo, pageSize);
        return new ResponseEntity<>(itemService.getAllItem(page), HttpStatus.OK);
    }

    @PostMapping("createItem")
    public ResponseEntity<String> createItem(@RequestBody @Valid ItemDto request)
    {
        if (itemService.createItem(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);;

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("updateItem")
    public ResponseEntity<String> updateItem(@RequestBody @Valid ItemDto request)
    {
        if (itemService.updateItem(request))
            return new ResponseEntity<>(GlobalConstants.SUCCESS, HttpStatus.OK);;

        return new ResponseEntity<>(GlobalConstants.FAILED, HttpStatus.BAD_REQUEST);
    }
}
