package com.trainingsv2.repository;

import com.trainingsv2.domain.Item;
import com.trainingsv2.repository.base.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
//QuerydslPredicateExecutor<Item>
public interface ItemRepository extends BaseRepository<Item, Integer> {
}
