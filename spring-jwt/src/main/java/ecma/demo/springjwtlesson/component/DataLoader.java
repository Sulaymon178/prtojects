package ecma.demo.springjwtlesson.component;

import ecma.demo.springjwtlesson.entity.Role;
import ecma.demo.springjwtlesson.entity.Users;
import ecma.demo.springjwtlesson.entity.enums.RoleName;
import ecma.demo.springjwtlesson.repository.RoleRepository;
import ecma.demo.springjwtlesson.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String mode;


    @Override
    public void run(String... args) throws Exception {

        if(mode.equalsIgnoreCase("always")){
            Role role = roleRepository.save(new Role(1, RoleName.ROLE_ADMIN,"admin rolidasiz"));
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            userRepository.save(new Users("Sulaymon","Madaminov",passwordEncoder.encode("root123"),"admin", roles));
        }
    }
}
