package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cake_order")
@Data
public class CakeOrder extends IdEntity implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private String status;

    @Column(name = "receive_status")
    private String receiveStatus;

    @Column(name = "delivery_id")
    private String deliveryId;

    @Column(name = "delivery_user")
    private String deliveryUser;

    @Column(name = "delivery_time")
    private Date deliveryTime;


    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;

}