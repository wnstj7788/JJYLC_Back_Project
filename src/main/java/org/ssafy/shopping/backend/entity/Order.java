package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String memberMail;

    @Column(nullable = false)
    private int itemId;

    @Column(length = 50, nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Timestamp orderDate;

    @Column(length = 100, nullable = false)
    private String orderPrice;

    @Column(nullable = false)
    private String discountPer;

    @Column(length = 50, nullable = false)
    private String img_path;

}