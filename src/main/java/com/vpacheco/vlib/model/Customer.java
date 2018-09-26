package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.DateAudit;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch =  FetchType.LAZY, optional = false)
  private User user;

  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<Requisition> requisitions;
}
