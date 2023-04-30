package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int quantity;
    @Column(length = 100, nullable = false)
    private String memberMail;

    @Column(nullable = false)
    private int itemId;
    @Column(nullable = false)
    private int optionsId;


    @Override
    public String toString() {
        return "id = " +id +" itemid : " + itemId + " memberMail : " +memberMail;
    }
}