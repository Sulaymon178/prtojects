package org.example.repository;

import org.example.entitiy.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupsRepository extends JpaRepository<Groups, UUID> {
  void deleteById(UUID uuid);
}
