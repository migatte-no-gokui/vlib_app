package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.UserDateAudit;
import com.vpacheco.vlib.validator.ValidDeliverDate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@ValidDeliverDate
public class Requisition extends UserDateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @NotNull
  private Book book;

  private LocalDateTime pickupDate;

  private LocalDateTime deliveryDate;

  @ManyToOne
  private User authorizedBy;

  @ManyToOne(optional = false)
  private Customer customer;

  @NotNull
  // when user doesn't pick up on time
  private boolean cancelled = false;
}
