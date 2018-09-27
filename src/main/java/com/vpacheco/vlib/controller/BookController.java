package com.vpacheco.vlib.controller;

import com.vpacheco.vlib.payload.BookRequest;
import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.vpacheco.vlib.resource.BookResource;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RepositoryRestController
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping(path="/books/findByLanguage", produces = "application/hal+json")
  public ResponseEntity<Resources<BookResource>> findByLanguage(
      @Valid @RequestBody BookRequest bookRequest
  ) {
    Resources<BookResource> resourcesByLanguage =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) ->
                repo.findByLanguage(criteria, pageable)
        );
    resourcesByLanguage.add(
        linkTo(methodOn(BookController.class).findByLanguage(bookRequest))
            .withRel("books"));

    return new ResponseEntity<>(resourcesByLanguage, HttpStatus.OK);
  }

  @GetMapping(path="/books/findByTitle", produces="application/hal+json")
  public ResponseEntity<Resources<BookResource>> findByTitle(
      @Valid @RequestBody BookRequest bookRequest
      ) {

    Resources<BookResource> resourcesByTitle =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) -> repo.findByTitle(criteria, pageable));
    resourcesByTitle.add(
        linkTo(methodOn(BookController.class).findByTitle(bookRequest))
        .withRel("books"));

   return new ResponseEntity<>(resourcesByTitle, HttpStatus.OK);
  }

  @GetMapping(path="/books/findByISBN", produces="application/hal+json")
  public ResponseEntity<Resource<BookResource>> findByISBN(
      @RequestParam(value = "isbn") String isbn
  ) {

    Resource<BookResource> resourceByISBN = bookService.createResource(isbn,
        (String criteria, BookRepository repo) -> repo.findByIsbn(criteria));

    resourceByISBN.add(
        linkTo(methodOn(BookController.class).findByISBN(isbn))
        .withRel("book"));

    return new ResponseEntity<>(resourceByISBN, HttpStatus.OK);
  }

  @GetMapping(path="/books/findAvailable", produces = "application/hal+json")
  public ResponseEntity<Resources<BookResource>> findAvailable(
      @Valid @RequestBody BookRequest bookRequest
  ) {
    Resources<BookResource> availableResources =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) -> repo.findAvailable(pageable));
    availableResources.add(
        linkTo(methodOn(BookController.class).findAvailable(bookRequest))
        .withRel("books"));

    return new ResponseEntity<>(availableResources, HttpStatus.OK);
  }

  @GetMapping(path="/books/findUnavailable", produces = "application/hal+json")
  public ResponseEntity<Resources<BookResource>> findUnavailable(
      @Valid @RequestBody BookRequest bookRequest
  ) {
    Resources<BookResource> unavailableResources =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) -> repo.findUnavailable(pageable));
    unavailableResources.add(
        linkTo(methodOn(BookController.class).findUnavailable(bookRequest))
            .withRel("books"));

    return new ResponseEntity<>(unavailableResources, HttpStatus.OK);
  }

  @GetMapping(path="/books/findByPublisher", produces = "application/hal+json")
  public ResponseEntity<Resources<BookResource>> findByPublisher(
      @Valid @RequestBody BookRequest bookRequest
  ) {
    Resources<BookResource> resourcesByPublisher =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) ->
                repo.findByPublisher(criteria, pageable)
        );
    resourcesByPublisher.add(
        linkTo(methodOn(BookController.class).findByPublisher(bookRequest))
            .withRel("books"));

    return new ResponseEntity<>(resourcesByPublisher, HttpStatus.OK);
  }

  @GetMapping(path="/books/findByGenre", produces = "application/hal+json")
  public ResponseEntity<Resources<BookResource>> findByGenre(
      @Valid @RequestBody BookRequest bookRequest
  ) {
    Resources<BookResource> resourcesByGenre =
        bookService.createResources(bookRequest,
            (String criteria, BookRepository repo, Pageable pageable) ->
                repo.findByGenre(criteria, pageable)
        );
    resourcesByGenre.add(
        linkTo(methodOn(BookController.class).findByPublisher(bookRequest))
            .withRel("books"));

    return new ResponseEntity<>(resourcesByGenre, HttpStatus.OK);
  }
}
