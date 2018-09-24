package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitionRepository extends JpaRepository<Requisition, Long> {
  List<Requisition> findByCancelledIsFalse();
}
