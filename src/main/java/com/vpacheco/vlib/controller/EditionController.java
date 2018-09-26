package com.vpacheco.vlib.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public class EditionController {

  private EditionRepository editionRepo;

  public EditionController(EditionRepository editionRepo) {
    this.editionRepo = editionRepo;
  }
}
