package org.example.uberprojectauthservice.helpers;

import org.example.uberprojectauthservice.models.passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// we need this class because spring security works on userDetails polymorphic type for auth;

public class authPassengerDetails extends passenger implements UserDetails {

    private String username;

    private String password;

    public authPassengerDetails(passenger passenger){
        this.username = passenger.getEmail();
        this.password = passenger.getPassword();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // below methods are not much of a use;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
