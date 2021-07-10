package com.demo.entitiy;

import com.demo.entitiy.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {


    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firsName;

    private String lastName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String parol;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

//    private String password;

    public Users(String firsName, String lastName, String phoneNumber, Gender gender, String email, String login, String parol, List<Role> roles) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.login = login;
        this.parol = parol;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return this.roles;
    }

    @Override
    public String getPassword() {
        return this.parol;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
