package com.example.website.service;

import com.example.website.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    /*
    *CustomUserDetails is nodig omdat Spring Security specifiek met een UserDetails-implementatie werkt.
    *Door een adapter zoals CustomUserDetails te maken,
    *kun je Spring Security integreren met jouw UserModel zonder je domeinlogica te vervuilen of je code minder flexibel te maken
    *
    *
    * */

    private final UserModel user;

    public CustomUserDetails(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

}
