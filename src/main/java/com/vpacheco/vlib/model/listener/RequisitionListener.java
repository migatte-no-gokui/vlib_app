package com.vpacheco.vlib.model.listener;

import com.vpacheco.vlib.model.Book;
import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.model.RequisitionStatus;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class RequisitionListener {

  @PrePersist
  public void setCopiesOnCreate(Requisition requisition) {
    Book book = requisition.getBook();
    int availableCopies = book.getCopiesAvailable();
    book.setCopiesAvailable(--availableCopies);
  }

  @PreUpdate
  public void setCopiesOnUpdate(Requisition requisition) {
    Book book = requisition.getBook();
    RequisitionStatus status = requisition.getStatus();

    stateManager(requisition);

    int availableCopies = book.getCopiesAvailable();
    book.setCopiesAvailable(--availableCopies);
    if (status ==  RequisitionStatus.CANCELLED ||
        status == RequisitionStatus.DELIVERED)
      book.setCopiesAvailable(++availableCopies);
    else if (status == RequisitionStatus.PICKED_UP)
      book.setCopiesAvailable(--availableCopies);
  }

  public void stateManager(Requisition requisition) {
    LocalDateTime pickupDate = requisition.getPickupDate();
    LocalDateTime deliveryDate = requisition.getDeliveryDate();
    if (pickupDate != null && deliveryDate == null)
      requisition.setStatus(RequisitionStatus.PICKED_UP);
    else if (pickupDate != null && deliveryDate != null)
      requisition.setStatus(RequisitionStatus.DELIVERED);
  }


}
