package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.User;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class SignupResource extends ResourceSupport {

  @Getter
  private String name;

  @Getter
  private String username;

  @Getter
  private String email;

  @Getter
  private String password;

  public SignupResource(User user) {
    this.name = user.getName();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.password = user.getPassword();
  }
}
