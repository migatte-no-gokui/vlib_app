package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.model.Role;
import com.vpacheco.vlib.model.User;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Set;

public class UserProfileResource extends ResourceSupport {

  @Getter
  private final String name;

  @Getter
  private final String username;

  @Getter
  private final String email;

  @Getter
  private final Set<Role> roles;

  @Getter
  private final List<Requisition> authorizedRequisitions;

  public UserProfileResource(User user) {
    this.name = user.getName();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.roles = user.getRoles();
    this.authorizedRequisitions = user.getAuthorizedRequisitions();
  }
}
