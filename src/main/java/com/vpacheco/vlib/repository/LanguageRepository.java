package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
  Boolean existsByName(String name);
}
