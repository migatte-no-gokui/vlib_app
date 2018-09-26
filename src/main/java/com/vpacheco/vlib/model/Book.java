package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book extends UserDateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 140)
  private String title;

  @Size(min = 10, max = 500)
  private String description;

  @OneToMany(
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  private List<Edition> editions = new ArrayList<>();

  @NotBlank
  @Size
  private int copiesAvailable;

  @ManyToOne(optional = false)
  private Genre genre;
}
