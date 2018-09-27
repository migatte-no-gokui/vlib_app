package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.payload.RequisitionRequest;
import com.vpacheco.vlib.resource.RequisitionResource;
import com.vpacheco.vlib.resource.assembler.RequisitionResourceAssembler;
import com.vpacheco.vlib.service.RequisitionService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RepositoryRestController
public class RequisitionController {

  private RequisitionService requisitionService;

  public RequisitionController(RequisitionService requisitionService) {
    this.requisitionService = requisitionService;
  }

  @PostMapping(path="/requisitions", produces = "application/hal+json")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Resource<RequisitionResource>> create(
      @Valid @RequestBody RequisitionRequest requisitionRequest
  ){
    Requisition requisition = requisitionService.createRequisition(requisitionRequest);

    RequisitionResource assembledRequisitionResource =
        new RequisitionResourceAssembler().toResource(requisition);
    Resource<RequisitionResource> requisitionResource =
        new Resource<>(assembledRequisitionResource);

    requisitionResource.add(
        linkTo(methodOn(RequisitionController.class).create(requisitionRequest))
        .withRel("requisitions"));

    return new ResponseEntity<>(requisitionResource, HttpStatus.OK);
  }
}
