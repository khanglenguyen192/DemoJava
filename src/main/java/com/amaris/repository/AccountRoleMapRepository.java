package com.amaris.repository;

import com.amaris.domain.AccountRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, Integer> {
    @Query(value = "SELECT accountRoleMap from AccountRoleMap as accountRoleMap where accountRoleMap.account.accountId = :accountId", nativeQuery = false)
    List<AccountRoleMap> findAllByAccountId(int accountId);
}
