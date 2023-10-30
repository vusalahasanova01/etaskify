package com.project.etaskify.model.entity;

import com.project.etaskify.model.dto.UserRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private Long organizationId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getUserRoleAsEnum().name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getUserRole() {
        return userRole.getId();
    }

    public UserRole getUserRoleAsEnum() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = UserRole.of(userRole);
    }

    public void setUserRoleAsEnum(UserRole userRole) {
        this.userRole = userRole;
    }
}