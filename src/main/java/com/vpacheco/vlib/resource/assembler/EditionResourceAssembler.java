package com.vpacheco.vlib.resource.assembler;

import com.vpacheco.vlib.controller.EditionController;
import com.vpacheco.vlib.model.Edition;
import com.vpacheco.vlib.resource.EditionResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class EditionResourceAssembler extends
    ResourceAssemblerSupport<Edition, EditionResource> {

  public EditionResourceAssembler() {
    super(EditionController.class, EditionResource.class);
  }

  @Override
  public EditionResource toResource(Edition edition) {
    return createResourceWithId(edition.getId(), edition);
  }

  @Override
  protected EditionResource instantiateResource(Edition edition) {
    return new EditionResource(edition);
  }
}
