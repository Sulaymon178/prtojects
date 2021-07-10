package ecma.demo.springjwtlesson.repository;

import ecma.demo.springjwtlesson.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Users findByLogin(String login);

}
