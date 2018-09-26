package com.vpacheco.vlib.service;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.payload.BookRequest;
import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.resource.BookResource;
import com.vpacheco.vlib.resource.assembler.BookResourceAssembler;
import com.vpacheco.vlib.service.helper.BookResponseFunction;
import com.vpacheco.vlib.service.helper.BooksResponseFunction;
import com.vpacheco.vlib.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookRepository repo;

  public Resource<BookResource> createResource(String criteria,
                                               BookResponseFunction f) {
    Book book = f.apply(criteria, repo);

    BookResource bookResource =
        new BookResourceAssembler().toResource(book);
    Resource<BookResource> resource =
        new Resource<BookResource>(bookResource);

    return resource;
  }

  public Resources<BookResource> createResources(BookRequest bookRequest,
                                                  BooksResponseFunction f) {
    Pageable pageable = getPageable(bookRequest);
    Page<Book> books = f.apply(bookRequest.getCriteria(), repo, pageable);

    List<BookResource> bookResources =
        new BookResourceAssembler().toResources(books);
    Resources<BookResource> resources =
        new Resources<BookResource>(bookResources);

    return resources;
  }

  private Pageable getPageable(BookRequest bookRequest) {
    String direction = bookRequest.getDirection();
    int page = bookRequest.getPage();
    Sort sortBy = direction.toLowerCase().compareTo("asc") == 0 ?
        Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();

    return PageRequest.of(page, AppConstants.DEFAULT_PAGE_SIZE, sortBy);
  }
}
