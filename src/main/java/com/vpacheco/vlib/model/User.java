package com.vpacheco.vlib.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import com.vpacheco.vlib.model.audit.DateAudit;

@Entity
@Data
//@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
public class User extends DateAudit {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "GUID")
  @GenericGenerator(
      name = "GUID",
      strategy = "org.hibernate.id.GUIDGenerator"
  )
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
}
