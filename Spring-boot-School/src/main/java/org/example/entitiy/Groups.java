package org.example.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entitiy.enums.GroupStatus;
import org.example.payload.ReqGroup;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GroupStatus groupStatus;

//
//    @Fetch(FetchMode.SUBSELECT)
//    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "groups")
//    private List<Student> students = new ArrayList<>();

    public Groups(String name, GroupStatus groupStatus) {
        this.name = name;
        this.groupStatus = groupStatus;
    }


}
