package com.vpacheco.vlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vpacheco.vlib.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);
  List<User> findByIdIn(List<Long> userIds);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
