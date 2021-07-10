package ecma.demo.springjwtlesson.repository;

import ecma.demo.springjwtlesson.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
