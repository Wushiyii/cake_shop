package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order")
@Data
public class Order extends IdEntity implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private String status;

    @Column(name = "suer_id")
    private String suerId;

    @Column(name = "suer_user")
    private String suerUser;

    @Column(name = "suer_time")
    private Date suerTime;


    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

}