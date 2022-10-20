package com.amaris.dto.account;

import com.amaris.common.utils.GlobalConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String email;

    @NotBlank(message = GlobalConstants.NOT_BLANK)
    @Length(max = 64)
    private String password;
}
