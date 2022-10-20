package com.amaris.dto.catalog;

import com.amaris.common.utils.GlobalConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
