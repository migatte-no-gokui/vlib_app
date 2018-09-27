package com.vpacheco.vlib.service;

import com.vpacheco.vlib.exception.ResourceNotFoundException;
import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.model.Customer;
import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.model.RoleName;
import com.vpacheco.vlib.payload.RequisitionRequest;
import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.repository.CustomerRepository;
import com.vpacheco.vlib.repository.RequisitionRepository;
import com.vpacheco.vlib.repository.UserRepository;
import com.vpacheco.vlib.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisitionService {

  @Autowired
  private RequisitionRepository requisitionRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomerRepository customerRepository;

  public Requisition createRequisition(RequisitionRequest requisitionRequest) {

    Long customerId = requisitionRequest.getCustomerId();
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    Long bookId = requisitionRequest.getBookId();
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

    Requisition requisition = new Requisition();
    requisition.setBook(book);
    requisition.setCustomer(customer);

    return requisition;
  }
}
