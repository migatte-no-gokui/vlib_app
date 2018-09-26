package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
