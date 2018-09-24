package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.UserDateAudit;
import com.vpacheco.vlib.validator.ValidDeliverDate;
import lombok.Builder;
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

  @Column(updatable = false)
  private User authorizedBy;

  // when user doesn't pick up on time
  private boolean cancelled = false;

  // move this to service or controller
  public boolean isAdmin(User user) {
    return user.getRoles().stream()
        .anyMatch(r -> r.getName() == RoleName.ROLE_ADMIN);
  }
}
