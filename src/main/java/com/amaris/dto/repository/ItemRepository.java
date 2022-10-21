package com.amaris.dto.repository;

import com.amaris.domain.Item;
import com.amaris.dto.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends BaseRepository<Item, Integer> {
}
