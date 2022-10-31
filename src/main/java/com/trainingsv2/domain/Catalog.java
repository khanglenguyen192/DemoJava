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
@Table(name = "catalogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private Integer catalogId;

    private String catalogName;

    private String description;

//    @JsonIgnore
//    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Item> items;
}
