package com.amaris.service.mapper;

import com.amaris.domain.Item;
import com.amaris.dto.item.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemDto itemDto);

    Item toItem(@MappingTarget Item item, ItemDto itemDto);

    @Mapping(source = "catalog.catalogId", target = "catalogId")
    ItemDto toItemDto(Item item);

    List<ItemDto> toItemDtos(List<Item> items);
}
