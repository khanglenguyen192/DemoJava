package com.trainingsv2.repository;

import com.trainingsv2.domain.Catalog;
import com.trainingsv2.repository.base.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
// QuerydslPredicateExecutor<Catalog>
public interface CatalogRepository extends BaseRepository<Catalog, Integer> {
}
