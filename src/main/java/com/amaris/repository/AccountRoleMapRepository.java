package com.amaris.repository;

import com.amaris.domain.AccountRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleMapRepository extends JpaRepository<AccountRoleMap, Integer> {
}
