package com.vpacheco.vlib.service.helper;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface BooksResponseFunction {
  Page<Book> apply(String criteria, BookRepository repo, Pageable pageable);
}