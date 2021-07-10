package com.demo.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region extends AutoId {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "region", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Users> users;

}
