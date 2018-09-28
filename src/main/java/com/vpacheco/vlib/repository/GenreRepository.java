package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
  Boolean existsByName(String name);
}
