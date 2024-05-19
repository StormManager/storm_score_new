package com.storm.score.security;


import com.storm.score.em.UserRole;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final Long userId;
    private final String email;

    private final String name;
    private final String userPwd;

    @Getter
    private final Set<UserRole> userRoleList;

    public UserDetailsImpl(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getNickName();
        this.userPwd = user.getUserPwd();
        this.userRoleList = user.getUserRoleSet();
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.userRoleList.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .toList();
    }

    public void isForbid(UserRole userRole) {
        this.userRoleList.stream()
                .filter(role -> role.equals(userRole))
                .findFirst()
                .orElseThrow(() -> new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION,"해당 권한이 없습니다. 권한명 : " + userRole.name()));
    }
}