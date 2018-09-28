package com.vpacheco.vlib.service;

import com.vpacheco.vlib.exception.BadRequestException;
import com.vpacheco.vlib.exception.ResourceNotFoundException;
import com.vpacheco.vlib.model.*;
import com.vpacheco.vlib.payload.RequisitionRequest;
import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.repository.CustomerRepository;
import com.vpacheco.vlib.repository.RequisitionRepository;
import com.vpacheco.vlib.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  private static final Logger logger = LoggerFactory.getLogger(RequisitionService.class);

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

    return requisitionRepository.save(requisition);
  }

  public List<Requisition> findByCustomer(String username, Long customerId) {

    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResourceNotFoundException("User", "username", username));

    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

    if (user.getId() == customer.getUser().getId() ||
        user.getRoles().stream().anyMatch(r -> r.getName() == RoleName.ROLE_ADMIN)) {
      return customer.getRequisitions();
    } else {
      throw new BadRequestException("Not authorized to access this resource");
    }
  }
}
