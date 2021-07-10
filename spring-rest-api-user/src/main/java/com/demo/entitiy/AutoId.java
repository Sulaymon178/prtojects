package com.demo.entitiy;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AutoId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
}
