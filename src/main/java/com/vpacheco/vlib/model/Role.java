package com.vpacheco.vlib.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(generator = "GUID")
  @GenericGenerator(
      name = "GUID",
      strategy = "org.hibernate.id.GUIDGenerator"
  )
  private Long id;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(length = 60)
  private RoleName name;

  public Role(RoleName name) {
    this.name = name;
  }
}
