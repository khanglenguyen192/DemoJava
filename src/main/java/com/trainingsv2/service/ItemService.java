package com.trainingsv2.service;

import com.trainingsv2.dto.base.PageResponse;
import com.trainingsv2.dto.item.ItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<ItemDto> getAllItem();

    PageResponse<ItemDto> getAllItem(Pageable page);

    boolean createItem(ItemDto itemDto);

    boolean updateItem(ItemDto itemDto);

    PageResponse<ItemDto> findItem(Integer itemId, String itemName, Integer catalogId, String catalogName, String createBy, Pageable page);
}
