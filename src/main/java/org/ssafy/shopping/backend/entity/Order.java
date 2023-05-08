package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "items_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String memberMail;
    @Column(nullable = false)
    private int quantity;
    @Column
    private String info;

    @Column(nullable = false)
    private int itemsId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private int optionsId;

}