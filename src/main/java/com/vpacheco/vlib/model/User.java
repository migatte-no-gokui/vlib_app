package com.vpacheco.vlib.model;

import lombok.AccessLevel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.vpacheco.vlib.model.audit.DateAudit;

@Entity
@Data
public class User extends DateAudit {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @Size(max = 40)
  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(max = 60)
  @Email
  @Column(unique = true)
  private String email;

  @NotBlank
  @Size(max = 100)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<Role> roles = new HashSet<>();

  @OneToOne(fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  private Customer customer;

  @OneToMany(
      fetch = FetchType.LAZY
  )
  private List<Requisition> authorizedRequisitions;

  public User() {

  }

  public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
