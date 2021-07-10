package com.demo.entitiy;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users extends AutoId {

    private String Name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region  region;

}
