package com.vpacheco.vlib.repository;

import com.vpacheco.vlib.model.Role;

import com.vpacheco.vlib.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
