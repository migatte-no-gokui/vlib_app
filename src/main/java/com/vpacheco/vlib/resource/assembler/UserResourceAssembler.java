package com.vpacheco.vlib.resource.assembler;

import com.vpacheco.vlib.controller.UserController;
import com.vpacheco.vlib.model.User;
import com.vpacheco.vlib.resource.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler
  extends ResourceAssemblerSupport<User, UserResource> {

  public UserResourceAssembler() {
    super(UserController.class, UserResource.class);
  }

  @Override
  protected UserResource instantiateResource(User user) {
    return new UserResource(user);
  }

  @Override
  public UserResource toResource(User user) {
    return createResourceWithId(user.getId(), user);
  }
}
