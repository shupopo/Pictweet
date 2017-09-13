package com.example.util;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserCustom extends User {
    private static final long serialVersionUID = 1L;
    private Long id;

    public UserCustom(Long id, String username, String password, Set<? extends GrantedAuthority> autorities) {
    //Set<? extends GrantedAuthority>については
    	//http://qiita.com/sukoyakarizumu/items/a95d88e294940c31b6fc
    	super(username, password, autorities);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
