package com.amaris.domain;

import com.amaris.domain.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    private String itemName;

    private int quantity;

    private String description;

    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id")
    private Catalog catalog;
}
