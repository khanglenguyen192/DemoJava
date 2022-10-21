package com.amaris.dto.repository;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.domain.UserRole;
import com.amaris.exception.impl.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    default List<UserRole> findAllById(Iterable<Integer> ids)
    {
        List<UserRole> results = new ArrayList<UserRole>();

        for (Integer id : ids) {
            UserRole role = findById(id).orElseThrow(() -> new NotFoundException(GlobalConstants.ROLE_NOT_FOUND));
            results.add(role);
        }

        if (results.size() == 0)
            throw new NotFoundException(GlobalConstants.ROLE_NOT_FOUND);

        return results;
    }
}
