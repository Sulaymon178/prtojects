package com.demo.repository;

import com.demo.entitiy.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Users findByLogin(String login);
}
