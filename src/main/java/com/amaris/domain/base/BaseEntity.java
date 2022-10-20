package com.amaris.domain.base;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity {

    protected LocalDateTime createdDate;

    protected String createdBy;

    @Nullable
    @Column(nullable = true)
    protected LocalDateTime modifyDate;

    @Nullable
    @Column(nullable = true)
    protected String modifyBy;

    @Nullable
    @Column(nullable = true)
    protected LocalDateTime deleteDate;

    @Nullable
    @Column(nullable = true)
    protected String deleteBy;
}
