package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Customer;
import com.vpacheco.vlib.resource.assembler.RequisitionResourceAssembler;
import com.vpacheco.vlib.resource.assembler.UserResourceAssembler;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class CustomerResource extends ResourceSupport {

  private static final UserResourceAssembler
    userAssembler = new UserResourceAssembler();

  private static final RequisitionResourceAssembler
    requisitionAssembler = new RequisitionResourceAssembler();

  @Getter
  private final List<RequisitionResource> requisitions;

  @Getter
  private final UserResource user;

  public CustomerResource(Customer customer) {
    this.requisitions =
        requisitionAssembler.toResources(customer.getRequisitions());
    this.user =
      userAssembler.toResource(customer.getUser());
  }
}
