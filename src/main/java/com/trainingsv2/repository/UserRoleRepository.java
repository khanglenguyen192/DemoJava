package com.trainingsv2.repository;

import com.trainingsv2.common.utils.GlobalConstants;
import com.trainingsv2.domain.UserRole;
import com.trainingsv2.repository.base.BaseRepository;
import com.trainingsv2.common.exception.impl.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole, Integer> {
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
