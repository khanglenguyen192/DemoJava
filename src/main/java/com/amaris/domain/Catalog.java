package com.amaris.domain;

import com.amaris.domain.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "catalogs")
@Data
@NoArgsConstructor
public class Catalog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private Integer catalogId;

    private String catalogName;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;
}
