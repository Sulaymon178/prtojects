package org.example.repository;

import org.example.entitiy.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {
}
