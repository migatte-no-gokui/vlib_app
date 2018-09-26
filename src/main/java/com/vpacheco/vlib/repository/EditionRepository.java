package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
