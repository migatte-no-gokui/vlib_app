package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
