package com.vpacheco.vlib.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Settings {
  @Value("${app.config.reservationExpiry}")
  private int reservationExpiry;

  @Value("${app.config.requisitionExpiry}")
  private int requisitionExpiry;

  @Value("${app.config.openingTime}")
  private String openingTime;

  @Value("${app.config.closingTime}")
  private String closingTime;
}
