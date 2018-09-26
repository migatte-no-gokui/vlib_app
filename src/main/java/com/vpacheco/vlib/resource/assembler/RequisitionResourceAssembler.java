package com.vpacheco.vlib.resource.assembler;

import com.vpacheco.vlib.controller.RequisitionController;
import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.resource.RequisitionResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class RequisitionResourceAssembler
  extends ResourceAssemblerSupport<Requisition, RequisitionResource> {

  public RequisitionResourceAssembler() {
    super(RequisitionController.class, RequisitionResource.class);
  }

  @Override
  protected RequisitionResource instantiateResource(Requisition requisition) {
    return new RequisitionResource(requisition);
  }

  @Override
  public RequisitionResource toResource(Requisition requisition) {
    return createResourceWithId(requisition.getId(), requisition);
  }
}
