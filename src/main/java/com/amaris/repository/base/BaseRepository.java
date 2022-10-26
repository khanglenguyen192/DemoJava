package com.amaris.repository.base;

import com.amaris.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    default T insert(T entity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetails user = (UserDetails) auth.getPrincipal();
            if (user != null)
                entity.setCreatedBy(user.getUsername());
        }
        entity.setCreatedDate(LocalDateTime.now());

        return save(entity);
    }

    default T update(T entity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetails user = (UserDetails) auth.getPrincipal();
            if (user != null)
                entity.setModifyBy(user.getUsername());
        }
        entity.setModifyDate(LocalDateTime.now());

        return save(entity);
    }
}
