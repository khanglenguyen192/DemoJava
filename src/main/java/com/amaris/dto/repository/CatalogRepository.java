package com.amaris.dto.repository;

import com.amaris.domain.Catalog;
import com.amaris.dto.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends BaseRepository<Catalog, Integer> {
}
