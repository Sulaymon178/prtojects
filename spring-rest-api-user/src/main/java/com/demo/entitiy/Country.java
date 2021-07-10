package com.demo.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends AutoId {
    private String name;

    public Country(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Region> regions;
}
