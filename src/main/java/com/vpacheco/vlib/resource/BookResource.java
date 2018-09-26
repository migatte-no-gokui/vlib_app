package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Book;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.Instant;
import java.time.LocalDate;

public class BookResource extends ResourceSupport {
  @Getter
  private final String title;

  @Getter
  private final String description;

  @Getter
  private final Instant createdAt;

  @Getter
  private final int copiesAvailable;

  @Getter
  private final String genre;

  @Getter
  private final String publisher;

  @Getter
  private final String author;

  @Getter
  private final String isbn;

  @Getter
  private final LocalDate publicationDate;

  @Getter
  private final int pages;

  @Getter
  private final String language;

  public BookResource(Book book) {
    this.title = book.getTitle();
    this.description = book.getDescription();
    this.createdAt = book.getCreatedAt();
    this.genre = book.getGenre().getName();
    this.copiesAvailable = book.getCopiesAvailable();
    this.publisher = book.getPublisher().getName();
    this.author = book.getAuthor().getName();
    this.isbn = book.getIsbn();
    this.publicationDate = book.getPublicationDate();
    this.pages = book.getPages();
    this.language = book.getLanguage().getName();
  }
}
