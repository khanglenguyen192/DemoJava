package com.amaris.service.impls;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.Catalog;
import com.amaris.domain.Item;
import com.amaris.dto.base.PageResponse;
import com.amaris.dto.item.ItemDto;
import com.amaris.exception.impl.NotAllowException;
import com.amaris.exception.impl.NotFoundException;
import com.amaris.repository.CatalogRepository;
import com.amaris.repository.ItemRepository;
import com.amaris.service.ItemService;
import com.amaris.service.mapper.ItemMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CatalogRepository catalogRepository;
    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, CatalogRepository catalogRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.catalogRepository = catalogRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public PageResponse<ItemDto> getAllItem(Pageable page) {

        Page<Item> items = itemRepository.findAll(page);

        return new PageResponse<>(itemMapper.toItemDtos(items.toList()), page, items.getTotalPages());
    }

    @Override
    public List<ItemDto> getAllItem() {
        return itemMapper.toItemDtos(itemRepository.findAll());
    }

    @Override
    public boolean createItem(ItemDto itemDto) {
        itemRepository.findById(itemDto.getItemId()).ifPresent(
                item -> { throw new NotAllowException(GlobalConstants.DUPLICATE_ID); }
        );

        Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(
                () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
        );

        Item newItem = itemMapper.toItem(itemDto);
        newItem.setCatalogId(catalog.getCatalogId());
        return itemRepository.insert(newItem) != null;
    }

    @Override
    public boolean updateItem(ItemDto itemDto) {
        var item = itemRepository.findById(itemDto.getItemId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, GlobalConstants.ITEM_ID_NOT_FOUND)
        );
        itemMapper.toItem(item, itemDto);

        //Catalog change
        if (!itemDto.getCatalogId().equals(item.getCatalogId()))
        {
            Catalog catalog = catalogRepository.findById(itemDto.getCatalogId()).orElseThrow(
                    () -> new NotFoundException(GlobalConstants.CATALOG_ID_NOT_FOUND)
            );
            item.setCatalogId(catalog.getCatalogId());
        }
        return itemRepository.update(item) != null;
    }
}
