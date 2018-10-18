package com.jesse.onecake.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "user")
public class User extends IdEntity implements Serializable {

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
}
