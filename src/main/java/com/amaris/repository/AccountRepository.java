package com.amaris.repository;

import com.amaris.domain.Account;
import com.amaris.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Integer> {
    Account findByEmail(String email);
}
