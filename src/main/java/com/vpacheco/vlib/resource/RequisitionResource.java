package com.vpacheco.vlib.resource;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.resource.assembler.BookResourceAssembler;
import com.vpacheco.vlib.resource.assembler.CustomerResourceAssembler;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

public class RequisitionResource extends ResourceSupport {

  // customer, user and book
  private static final CustomerResourceAssembler
    customerAssembler = new CustomerResourceAssembler();

  private static final BookResourceAssembler
    bookAssembler = new BookResourceAssembler();

  @Getter
  private final BookResource book;

  @Getter
  private final LocalDateTime pickupDate;

  @Getter
  private final LocalDateTime deliveryDate;

  @Getter
  private final String authorizedBy;

  @Getter
  private final CustomerResource customer;

  public RequisitionResource(Requisition requisition){
    this.book = bookAssembler.toResource(requisition.getBook());
    this.pickupDate = requisition.getPickupDate();
    this.deliveryDate = requisition.getDeliveryDate();
    this.authorizedBy = requisition.getAuthorizedBy().getName();
    this.customer = customerAssembler.toResource(requisition.getCustomer());
  }
}
