package com.trainingsv2.domain;

import com.trainingsv2.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    private String itemName;

    private int quantity;

    private String description;

    @Column(name = "catalog_id")
    private Integer catalogId;

//    @ManyToOne
//    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id")
//    private Catalog catalog;
}
