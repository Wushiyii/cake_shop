package com.jesse.onecake.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CartDTO {

    private Integer id;

    private String name;

    private Integer quantity;

    private Double price;

    private String img;

    private String userId;

    private Double totalPrice;
}
