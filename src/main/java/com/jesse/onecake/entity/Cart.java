package com.jesse.onecake.entity;

import com.jesse.onecake.entity.base.IdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cart")
@Data
public class Cart extends IdEntity implements Serializable {


    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;


}