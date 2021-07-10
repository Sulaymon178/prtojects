package ecma.demo.springjwtlesson.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users implements UserDetails {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String parol;

    @Column(unique = true, nullable = false)
    private String login;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    public Users(String firstName, String lastName,  String parol, String login, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parol = parol;
        this.login = login;
        this.roles = roles;
    }
    //    private String password;

    @Override
    public String getPassword() {
        return this.parol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.login;
    }


    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

}
