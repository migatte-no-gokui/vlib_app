package com.vpacheco.vlib.service.helper;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.repository.BookRepository;

@FunctionalInterface
public interface BookResponseFunction {
  Book apply(String criteria, BookRepository repo);
}