package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order")
@Data
public class Order extends IdEntity implements Serializable {

    /**
     * 创建订单用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 订单状态 0:待确认,1:已确认,2:取消
     */
    @Column(name = "status")
    private String status;

    /**
     * 确认人id
     */
    @Column(name = "suer_id")
    private String suerId;

    /**
     * 确认人名称
     */
    @Column(name = "suer_user")
    private String suerUser;

    /**
     * 确认时间
     */
    @Column(name = "suer_time")
    private Date suerTime;

    /**
     * 创建订单人名称
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建订单时间
     */
    @Column(name = "create_time")
    private Date createTime;

}