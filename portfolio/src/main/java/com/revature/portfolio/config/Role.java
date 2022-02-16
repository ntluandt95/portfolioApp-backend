package com.revature.portfolio.config;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;
    private Role(String s){
        role = s;
    }
    @Override
    public String getAuthority() {
        return role;
    }
}
