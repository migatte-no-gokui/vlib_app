package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
  Boolean existsByName(String name);
}
