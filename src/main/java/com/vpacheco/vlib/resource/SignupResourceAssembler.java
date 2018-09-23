package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.controller.AuthController;
import com.vpacheco.vlib.model.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class SignupResourceAssembler extends ResourceAssemblerSupport<User, SignupResource> {
  public SignupResourceAssembler() {
    super(AuthController.class, SignupResource.class);
  }

  @Override
  protected SignupResource instantiateResource(User user) {
    return new SignupResource(user);
  }

  @Override
  public SignupResource toResource(User user) {
    return createResourceWithId(user.getId(), user);
  }
}
