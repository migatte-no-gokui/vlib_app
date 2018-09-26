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

  @Query("SELECT b FROM Book b INNER JOIN Edition e ON e.isbn = :isbn")
  Book findByISBN(String isbn);

  @Query("SELECT b FROM Book b where b.copiesAvailable > 0")
  Page<Book> findAvailable(Pageable pageable);

  @Query("SELECT b FROM Book b where b.copiesAvailable = 0")
  Page<Book> findUnavailable(Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Edition e ON e.language.name = :languageName")
  Page<Book> findByLanguage(String languageName, Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Edition e ON e.publisher.name = :publisherName")
  Page<Book> findByPublisher(String publisherName, Pageable pageable);

  @Query("SELECT b from Book b INNER JOIN Genre g ON b.genre.name = :genreName")
  Page<Book> findByGenre(String genreName, Pageable pageable);
}
