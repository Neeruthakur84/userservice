package com.vardhan.userservice.dtos;

import com.vardhan.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private String name;
    private String email;
    private String password;

    public static SignupResponseDto from(User user) {
        if(user == null) return null;

        SignupResponseDto dto = new SignupResponseDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;

    }

}
