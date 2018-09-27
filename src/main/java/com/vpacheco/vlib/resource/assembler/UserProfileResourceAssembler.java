package com.vpacheco.vlib.resource.assembler;

import com.vpacheco.vlib.controller.UserController;
import com.vpacheco.vlib.model.User;
import com.vpacheco.vlib.resource.UserProfileResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserProfileResourceAssembler
  extends ResourceAssemblerSupport<User, UserProfileResource>
{
  public UserProfileResourceAssembler() { super(UserController.class, UserProfileResource.class); }

  @Override
  protected UserProfileResource instantiateResource(User user) { return new UserProfileResource(user); }

  @Override
  public UserProfileResource toResource(User user) { return createResourceWithId(user.getId(), user); }
}
