package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Cake extends IdEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "img")
    private String img;

}