package com.vardhan.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vardhan.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonSerialize(as = CustomGrantedAuthority.class)
public class CustomGrantedAuthority implements GrantedAuthority {

    public CustomGrantedAuthority() {

    }
    String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getRoleName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
