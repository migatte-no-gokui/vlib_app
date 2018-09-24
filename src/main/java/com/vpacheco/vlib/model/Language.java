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
  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Edition> books = new ArrayList<>();
}
