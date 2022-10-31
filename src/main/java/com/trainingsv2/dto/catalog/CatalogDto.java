package com.trainingsv2.dto.catalog;

import com.trainingsv2.common.utils.GlobalConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CatalogDto {
    private Integer catalogId;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String catalogName;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String description;
}
