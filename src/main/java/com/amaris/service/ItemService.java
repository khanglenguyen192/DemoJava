package com.amaris.service;

import com.amaris.dto.item.ItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<ItemDto> getAllItem();

    List<ItemDto> getAllItem(Pageable page);

    boolean createItem(ItemDto itemDto);

    boolean updateItem(ItemDto itemDto);
}
