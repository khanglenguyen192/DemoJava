package com.amaris.repository;

import com.amaris.domain.Catalog;
import com.amaris.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends BaseRepository<Catalog, Integer> {
}
