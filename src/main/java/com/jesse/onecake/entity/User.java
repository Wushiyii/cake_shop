package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class User extends IdEntity implements Serializable {

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name="group")
    private String group;

    @Column(name="gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;
}
