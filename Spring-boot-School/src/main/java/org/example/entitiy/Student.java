package org.example.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    private Groups groups;

    @OneToOne( fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Passport passport;

    public Student(String firstName, String lastNAme, Groups groups, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastNAme;
        this.groups = groups;
        this.passport = passport;
    }
}
