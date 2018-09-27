package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition, Long> {
  @Query("SELECT r from Requisition r WHERE status = 1")
  List<Requisition> findByCancelledIsFalse();
}
