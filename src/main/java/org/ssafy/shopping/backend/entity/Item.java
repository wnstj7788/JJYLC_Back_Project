package org.ssafy.shopping.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "items")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    
    @Column(length = 50, nullable = false)
    private String imgPath;

    
    @Column(nullable = false)
    private String price;

    
    @Column(nullable = false)
    private String discountPer;
}
