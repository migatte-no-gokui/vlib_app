package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.repository.EditionRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public class EditionController {

  private EditionRepository editionRepo;

  public EditionController(EditionRepository editionRepo) {
    this.editionRepo = editionRepo;
  }
}
