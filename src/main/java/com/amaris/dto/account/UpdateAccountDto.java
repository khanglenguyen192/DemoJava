package com.amaris.dto.account;

import com.amaris.common.utils.GlobalConstants;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdateAccountDto {
    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 8)
    private String firstName;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String lastName;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String email;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 10)
    private String phone;
    
    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String accountNumber;
}
