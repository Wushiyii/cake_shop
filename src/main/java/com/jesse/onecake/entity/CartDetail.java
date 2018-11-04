package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cart_detail")
@Data
public class CartDetail extends IdEntity implements Serializable {

    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "cake_id")
    private String cakeId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;


}