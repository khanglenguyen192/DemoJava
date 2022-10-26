package com.amaris.repository;

import com.amaris.domain.Item;
import com.amaris.repository.base.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends BaseRepository<Item, Integer>, QuerydslPredicateExecutor<Item> {
}
