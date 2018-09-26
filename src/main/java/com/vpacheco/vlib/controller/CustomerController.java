package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.service.CustomerService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestController
public class CustomerController {

  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  //@PostMapping("/customers")
}
