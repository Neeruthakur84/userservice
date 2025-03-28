package com.vardhan.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vardhan.userservice.models.Role;
import com.vardhan.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonSerialize(as = CustomUserDetails.class)
public class CustomUserDetails implements UserDetails {
//    User user; Removed to calm down Jackson, since from method getPassword() or getUserName(), it expecting a variable named password and username
     String username;
     String password;
     boolean accountNonExpired;
     boolean accountNonLocked;
     boolean credentialsNonExpired;
     boolean enabled;
    List<CustomGrantedAuthority> authorities;

    public CustomUserDetails() {}
    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new CustomGrantedAuthority(role));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
