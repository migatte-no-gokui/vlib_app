package com.vpacheco.vlib.service;

import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private BookRepository bookRepository;
}
