package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.User;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class LoginResource extends ResourceSupport {

  @Getter
  private String username;

  @Getter
  private String password;

  public LoginResource(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
  }
}
