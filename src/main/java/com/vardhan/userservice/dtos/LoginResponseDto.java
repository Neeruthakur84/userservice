package com.vardhan.userservice.dtos;

import com.vardhan.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String tokenValue;

    public static LoginResponseDto from(Token token) {

        LoginResponseDto dto = new LoginResponseDto();
        dto.setTokenValue(token.getValue());
        return dto;
    }
}
