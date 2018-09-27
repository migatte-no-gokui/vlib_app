package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.exception.ResourceNotFoundException;
import com.vpacheco.vlib.model.User;
import com.vpacheco.vlib.repository.UserRepository;
import com.vpacheco.vlib.resource.UserProfileResource;
import com.vpacheco.vlib.resource.UserResource;
import com.vpacheco.vlib.resource.assembler.UserProfileResourceAssembler;
import com.vpacheco.vlib.resource.assembler.UserResourceAssembler;
import com.vpacheco.vlib.security.CurrentUser;
import com.vpacheco.vlib.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RepositoryRestController
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepo;

  @GetMapping(path = "/user/me", produces = "application/hal+json")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Resource<UserResource>> getCurrentUser(@CurrentUser UserPrincipal currentUser) {

    User user = userRepo.getOne(currentUser.getId());

    UserResource assembledUserResource =
        new UserResourceAssembler().toResource(user);
    Resource<UserResource> userResource =
        new Resource<>(assembledUserResource);

    userResource.add(
        linkTo(methodOn(UserController.class).getCurrentUser(currentUser))
        .withRel("user"));

    return new ResponseEntity<>(userResource, HttpStatus.OK);
  }

  @GetMapping(path = "/users/{username}", produces = "application/hal+json")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Resource<UserProfileResource>> getUserProfile(
      @PathVariable(value = "username") String username) {

    User user = userRepo.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

    UserProfileResource assembledUserProfileResource =
        new UserProfileResourceAssembler().toResource(user);
    Resource<UserProfileResource> userProfileResource =
        new Resource<>(assembledUserProfileResource);

    userProfileResource.add(
        linkTo(methodOn(UserController.class).getUserProfile(username))
        .withRel("userProfile"));

    return new ResponseEntity<>(userProfileResource, HttpStatus.OK);
  }
}
