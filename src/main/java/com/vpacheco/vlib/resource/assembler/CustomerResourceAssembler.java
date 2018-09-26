package com.vpacheco.vlib.resource.assembler;

import com.vpacheco.vlib.controller.CustomerController;
import com.vpacheco.vlib.model.Customer;
import com.vpacheco.vlib.resource.CustomerResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class CustomerResourceAssembler
  extends ResourceAssemblerSupport<Customer, CustomerResource> {

  public CustomerResourceAssembler() {
    super(CustomerController.class, CustomerResource.class);
  }

  @Override
  protected CustomerResource instantiateResource(Customer customer) {
    return new CustomerResource(customer);
  }

  @Override
  public CustomerResource toResource(Customer customer) {
    return createResourceWithId(customer.getId(), customer);
  }
}
