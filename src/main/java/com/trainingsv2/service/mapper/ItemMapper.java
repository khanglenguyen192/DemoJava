package com.trainingsv2.service.mapper;

import com.trainingsv2.domain.Item;
import com.trainingsv2.dto.item.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemDto itemDto);

    Item toItem(@MappingTarget Item item, ItemDto itemDto);

    ItemDto toItemDto(Item item);

    List<ItemDto> toItemDtos(List<Item> items);
}
