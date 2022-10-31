package com.trainingsv2.repository;

import com.trainingsv2.domain.AccountRoleMap;
import com.trainingsv2.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, Integer> {
    @Query(value = "SELECT accountRoleMap from AccountRoleMap as accountRoleMap where accountRoleMap.accountId = :accountId", nativeQuery = false)
    List<AccountRoleMap> findAllByAccountId(int accountId);

    @Query(value = "SELECT userRole FROM AccountRoleMap AS accountRoleMap " +
                   "INNER JOIN UserRole AS userRole ON accountRoleMap.roleId = userRole.id " +
                   "WHERE accountRoleMap.accountId = :accountId")
    List<UserRole> findAllRoleByAccountId(int accountId);
}