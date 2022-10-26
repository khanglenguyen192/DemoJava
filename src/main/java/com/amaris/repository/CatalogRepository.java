package com.amaris.repository;

import com.amaris.domain.Catalog;
import com.amaris.repository.base.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends BaseRepository<Catalog, Integer>, QuerydslPredicateExecutor<Catalog> {
}
