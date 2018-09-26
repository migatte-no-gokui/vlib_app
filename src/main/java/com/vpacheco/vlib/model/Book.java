package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.UserDateAudit;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

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
      fetch = FetchType.LAZY
  )

  @NotNull
  private int copiesAvailable;

  @ManyToOne(optional = false)
  private Genre genre;

  @ManyToOne
  private Publisher publisher;

  @ManyToOne(optional = false)
  private Author author;

  @ISBN
  @NotBlank
  private String isbn;

  @PastOrPresent
  @NotNull
  private LocalDate publicationDate;

  @Min(4)
  private int pages;

  @ManyToOne
  private Language language;
}
