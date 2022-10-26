package com.vti.halloween.security;

import com.vti.halloween.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserEntity userAccount) {
        List<SimpleGrantedAuthority> userRole = Arrays.asList(new SimpleGrantedAuthority("USER_ROLE"));
        List<SimpleGrantedAuthority> adminRole = Arrays.asList(new SimpleGrantedAuthority("USER_ROLE"), new SimpleGrantedAuthority("ADMIN_ROLE"));
        return UserPrincipal.builder()
                .id(userAccount.getId())
                .username(userAccount.getAccount())
                .password(userAccount.getPassword())
                .authorities(userAccount.getAccount().equals("tuan.nguyendinh") || userAccount.getAccount().equals("toan.nguyenxuan") ? adminRole : userRole)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
