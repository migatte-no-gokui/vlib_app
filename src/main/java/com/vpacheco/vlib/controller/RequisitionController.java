package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.payload.RequisitionRequest;
import com.vpacheco.vlib.resource.RequisitionResource;
import com.vpacheco.vlib.resource.assembler.RequisitionResourceAssembler;
import com.vpacheco.vlib.security.CurrentUser;
import com.vpacheco.vlib.security.UserPrincipal;
import com.vpacheco.vlib.service.RequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.lang.reflect.Field;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RepositoryRestController
public class RequisitionController {

  private RequisitionService requisitionService;

  private static final Logger logger = LoggerFactory.getLogger(RequisitionController.class);

  public RequisitionController(RequisitionService requisitionService) {
    this.requisitionService = requisitionService;
  }

  @PostMapping(path="/requisitions", produces = "application/hal+json")
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

    return new ResponseEntity<>(requisitionResource, HttpStatus.CREATED);
  }

  @GetMapping(path="/requisitions/customer/{customerId}", produces = "application/hal+json")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Resources<RequisitionResource>> findByCustomer(
      @PathVariable(value = "customerId") Long customerId
  ) {
    String currentUserName = getUsername();
    List<Requisition> requisitions = requisitionService.findByCustomer(currentUserName, customerId);

    List<RequisitionResource> assembledRequisitionResource =
        new RequisitionResourceAssembler().toResources(requisitions);
    Resources<RequisitionResource> requisitionResources =
        new Resources<>(assembledRequisitionResource);

    requisitionResources.add(
        linkTo(methodOn(RequisitionController.class).findByCustomer(customerId))
        .withRel("requisitions"));

    return new ResponseEntity<>(requisitionResources, HttpStatus.OK);
  }

  private String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    return username;
  }
}
