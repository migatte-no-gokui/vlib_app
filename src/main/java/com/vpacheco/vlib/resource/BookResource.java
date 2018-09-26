package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.resource.assembler.EditionResourceAssembler;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.Instant;
import java.util.List;

public class BookResource extends ResourceSupport {

  private static final EditionResourceAssembler
    editionAssembler = new EditionResourceAssembler();

  @Getter
  private final String title;

  @Getter
  private final Instant createdAt;

  @Getter
  private final List<EditionResource> editions;

  @Getter
  private final int copiesAvailable;

  @Getter
  private final String genre;

  public BookResource(Book book) {
    this.title = book.getTitle();
    this.createdAt = book.getCreatedAt();
    this.genre = book.getGenre().getName();
    this.editions = editionAssembler.toResources(book.getEditions());
    this.copiesAvailable = book.getCopiesAvailable();
  }
}
