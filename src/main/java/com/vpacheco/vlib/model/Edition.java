package com.vpacheco.vlib.model;

import com.vpacheco.vlib.model.audit.UserDateAudit;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class Edition extends UserDateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  @NotBlank
  private String description;

  @Column(nullable = false)
  @PastOrPresent
  private LocalDate publicationDate;

  @ISBN
  private String isbn;

  @Size(min = 3)
  @NotBlank
  private int pageNum;

  @ManyToOne(optional = false)
  private Publisher publisher;

  @NotBlank
  @ManyToOne(optional = false)
  private Language language;
}
