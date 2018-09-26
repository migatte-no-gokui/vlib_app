package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.payload.RequisitionRequest;
import com.vpacheco.vlib.resource.RequisitionResource;
import com.vpacheco.vlib.service.RequisitionService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@RepositoryRestController
public class RequisitionController {

  private RequisitionService requisitionService;

  public RequisitionController(RequisitionService requisitionService) {
    this.requisitionService = requisitionService;
  }

  @PostMapping(path="/requisitions", produces = "application/hal+json")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Resources<RequisitionResource>> create(
      @Valid @RequestBody RequisitionRequest requisitionRequest
  ){

  }

  @PutMapping(path = "/requisitions/{id}", produces = "application/hal+json")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Resource<RequisitionResource>> update(
      @Valid @RequestBody RequisitionRequest requisitionRequest
  ){

  }
}
