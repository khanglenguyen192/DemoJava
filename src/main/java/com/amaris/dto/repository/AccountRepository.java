package com.amaris.dto.repository;

import com.amaris.domain.Account;
import com.amaris.dto.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Integer> {
    Account findByEmail(String email);
}
