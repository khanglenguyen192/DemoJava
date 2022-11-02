package com.trainingsv2.service.mapper;

import com.trainingsv2.domain.ItemSale;
import com.trainingsv2.dto.item.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemSaleMapper {
    ItemSale toItemSale(ItemDto itemDto);
}
