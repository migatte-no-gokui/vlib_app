package com.vpacheco.vlib.service;

import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisitionService {

  @Autowired
  private RequisitionRepository requisitionRepository;

  @Autowired
  private BookRepository bookRepository;
}
