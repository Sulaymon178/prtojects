package com.demo.controller;

import com.demo.entitiy.Role;
import com.demo.entitiy.Users;
import com.demo.entitiy.enums.Gender;
import com.demo.payload.ReqUser;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("register")
    public String getRegister() {

        return "auth/register";
    }

    @GetMapping("sign")
    public String getSignPage() {

        return "auth/signin";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute ReqUser reqUser) {

        userRepository.save(new Users(
                reqUser.getFirstName(),
                reqUser.getLastName(),
                reqUser.getPhoneNumber(),
                Gender.valueOf(reqUser.getGender()),
                reqUser.getEmail(),
                reqUser.getLogin(),
                passwordEncoder.encode(reqUser.getParol()),//parolni heshlab beradi
                roleRepository.findAll()));

        return "redirect:/auth/sign";
    }
}
