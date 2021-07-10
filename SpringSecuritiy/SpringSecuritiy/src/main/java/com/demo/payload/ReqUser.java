package com.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqUser {
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String email;
    private String parol;
//    private String parolConfig;

}
