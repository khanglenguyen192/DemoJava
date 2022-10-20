package com.amaris.dto.item;

import com.amaris.common.utils.GlobalConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ItemDto {
    private Integer itemId;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String itemName;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String description;

    private Integer catalogId;
}
