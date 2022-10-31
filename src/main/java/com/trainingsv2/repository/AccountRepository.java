package com.trainingsv2.repository;

import com.trainingsv2.domain.Account;
import com.trainingsv2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Integer> {
    Account findByEmail(String email);
}
