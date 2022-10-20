package com.amaris.service.mapper;

import com.amaris.domain.Item;
import com.amaris.dto.item.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    public Item toItem(ItemDto itemDto);

    public Item toItem(@MappingTarget Item item, ItemDto itemDto);

    public ItemDto toItemDto(Item item);

    public List<ItemDto> toItemDtos(List<Item> items);
}
