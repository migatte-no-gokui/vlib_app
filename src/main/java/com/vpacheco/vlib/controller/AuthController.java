package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.exception.AppException;
import com.vpacheco.vlib.model.Customer;
import com.vpacheco.vlib.model.Role;
import com.vpacheco.vlib.model.RoleName;
import com.vpacheco.vlib.model.User;
import com.vpacheco.vlib.payload.ApiResponse;
import com.vpacheco.vlib.payload.JwtAuthenticationResponse;
import com.vpacheco.vlib.payload.LoginRequest;
import com.vpacheco.vlib.repository.CustomerRepository;
import com.vpacheco.vlib.repository.RoleRepository;
import com.vpacheco.vlib.repository.UserRepository;
import com.vpacheco.vlib.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User requestUser) {
    if (userRepository.existsByUsername(requestUser.getUsername())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(requestUser.getEmail())) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
          HttpStatus.BAD_REQUEST);
    }

    User result = createUser(requestUser, RoleName.ROLE_USER);

    Customer customer = new Customer();
    customer.setUser(result);
    customerRepository.save(customer);

    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{username}")
        .buildAndExpand(result.getUsername()).toUri();

    return ResponseEntity
        .created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/createAdmin")
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody User requestUser) {
    if (userRepository.existsByUsername(requestUser.getUsername())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(requestUser.getEmail())) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
          HttpStatus.BAD_REQUEST);
    }

    User user = createUser(requestUser, RoleName.ROLE_ADMIN);

    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{username}")
        .buildAndExpand(user.getUsername()).toUri();

    return ResponseEntity
        .created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }

  private User createUser(User requestUser, RoleName roleName) {
    // Creating user's account
    User user = new User(requestUser.getName(), requestUser.getUsername(),
        requestUser.getEmail(), requestUser.getPassword());

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    if (roleName == RoleName.ROLE_ADMIN){
      Role userRole = getRole(RoleName.ROLE_USER);
      Role adminRole = getRole(RoleName.ROLE_ADMIN);
      user.setRoles(Stream.of(userRole, adminRole).collect(Collectors.toSet()));
    } else {
      user.setRoles(Collections.singleton(getRole(roleName)));
    }

    return userRepository.save(user);
  }

  private Role getRole(RoleName roleName) {
    return roleRepository.findByName(roleName)
        .orElseThrow(() -> new AppException("User Role not set."));
  }
}
