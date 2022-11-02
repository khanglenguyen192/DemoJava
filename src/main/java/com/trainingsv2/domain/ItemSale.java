package com.trainingsv2.domain;

import com.trainingsv2.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSale extends BaseEntity {
    @MongoId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itemId;
    private Integer catalogId;
    private String itemName;
    private int quantity;
    private String description;

    public void inceaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
