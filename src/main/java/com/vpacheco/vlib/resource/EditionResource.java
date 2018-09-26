package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Edition;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

public class EditionResource extends ResourceSupport {

  @Getter
  private final String description;

  @Getter
  private final LocalDate publicationDate;

  @Getter
  private final String publisher;

  @Getter
  private final String isbn;

  @Getter
  private final String language;

  @Getter
  private final int pages;

  public EditionResource(Edition edition) {
    this.description = edition.getDescription();
    this.publicationDate = edition.getPublicationDate();
    this.publisher = edition.getPublisher().getName();
    this.isbn = edition.getIsbn();
    this.language = edition.getLanguage().getName();
    this.pages = edition.getPageNum();
  }
}
