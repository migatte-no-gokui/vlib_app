package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Page<Book> findByTitle(String title, Pageable pageable);

  Book findByIsbn(String isbn);

  @Query("SELECT b FROM Book b where b.copiesAvailable > 0")
  Page<Book> findAvailable(Pageable pageable);

  @Query("SELECT b FROM Book b where b.copiesAvailable = 0")
  Page<Book> findUnavailable(Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Language l ON l.name = :languageName")
  Page<Book> findByLanguage(String languageName, Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Publisher p ON p.name = :publisherName")
  Page<Book> findByPublisher(String publisherName, Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Genre g ON g.name = :genreName")
  Page<Book> findByGenre(String genreName, Pageable pageable);
}
