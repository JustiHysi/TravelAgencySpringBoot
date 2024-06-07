package com.example.TravelAgency.security;


import com.example.TravelAgency.entities.Role;
import com.example.TravelAgency.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(org.springframework.security.core.userdetails.User user) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();//Set me te gjitha rolet e perdoruesit
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();//Liste boshe me akseset
        for (Role role:roles){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.getRole()));//Iterimi i roleve tek lista boshe
        }
        return simpleGrantedAuthorityList;
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

