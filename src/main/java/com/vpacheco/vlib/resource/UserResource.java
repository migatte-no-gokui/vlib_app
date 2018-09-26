package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.User;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

  @Getter
  private final String name;

  @Getter
  private final String username;

  @Getter
  private final String email;

  public UserResource(User user) {
    this.name = user.getName();
    this.username = user.getUsername();
    this.email = user.getEmail();
  }
}
