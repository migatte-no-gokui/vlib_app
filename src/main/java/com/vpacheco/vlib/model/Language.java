package com.vpacheco.vlib.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Language {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 3)
  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Book> books = new ArrayList<>();
}
