package com.jesse.onecake.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {

    private String id; //订单ID,采用Integer是为了在Thymeleaf中可以被JS解析

    private String status;

    private Date createTime;

    private String operation;
}
