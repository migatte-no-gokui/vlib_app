package com.vpacheco.vlib.task;

import com.vpacheco.vlib.model.Requisition;
import com.vpacheco.vlib.model.Settings;
import com.vpacheco.vlib.repository.BookRepository;
import com.vpacheco.vlib.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class RequisitionCheckerTask {

  @Autowired
  private RequisitionRepository requisitionRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private Settings settings;

  @Scheduled(cron = "* * 07 * * ?")
  public void start() {
    requisitionRepository.findByCancelledIsFalse()
        .stream().map(requisition -> {
      if (isReservationExpired(requisition)) {
        requisition.setCancelled(true);
      }
          return requisition;
    });
  }

  public void test() {
    
  }

  private boolean isReservationExpired(Requisition requisition) {
    LocalDateTime createdAt = LocalDateTime.ofInstant(requisition.getCreatedAt(),
        ZoneOffset.UTC);
    return LocalDateTime.now().compareTo(createdAt) > settings.getReservationExpiry();
  }
}
