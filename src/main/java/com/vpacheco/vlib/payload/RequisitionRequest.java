package com.vpacheco.vlib.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequisitionRequest {
  @NotNull
  private Long customerId;
  @NotNull
  private Long bookId;

  private LocalDateTime pickupDate;
  private LocalDateTime deliveryDate;
  private Long authorizedById;
}
