package com.rateuni.backend.models.base_models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniUser implements UserDetails {
    private int id;
    private String facultyNumber;
    private String email;
    private String username;
    private String password;
    private String image;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniUser uniUser = (UniUser) o;
        return id == uniUser.id
                && Objects.equals(facultyNumber, uniUser.facultyNumber)
                && Objects.equals(email, uniUser.email)
                && Objects.equals(username, uniUser.username)
                && Objects.equals(password, uniUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyNumber, email, username, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));
        return roles;
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
