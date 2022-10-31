package com.trainingsv2.domain;

import com.trainingsv2.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSale extends BaseEntity {
    @MongoId
    private Integer id;
    private Integer itemId;
    private Integer catalogId;
    private int quantity;
    private String description;
}
