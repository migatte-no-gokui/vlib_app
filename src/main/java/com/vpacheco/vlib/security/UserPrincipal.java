package com.vpacheco.vlib.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpacheco.vlib.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
  @Getter
  private Long id;

  @Getter
  private String name;

  @Getter
  private String username;

  @Getter
  @JsonIgnore
  private String email;

  @Getter
  @JsonIgnore
  private String password;

  @Getter
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String name, String username, String email,
                      String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName().name())
    ).collect(Collectors.toList());

    return new UserPrincipal(
        user.getId(),
        user.getName(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        authorities
    );
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
