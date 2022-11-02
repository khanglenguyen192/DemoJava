package com.trainingsv2.domain;

import com.trainingsv2.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "carts")
public class Cart extends BaseEntity {
    @MongoId
    private Integer accountId;

    private List<ItemSale> itemSales = new ArrayList<>();

    public void addItemSale(ItemSale itemSale) {
        itemSales.add(itemSale);
    }
}
