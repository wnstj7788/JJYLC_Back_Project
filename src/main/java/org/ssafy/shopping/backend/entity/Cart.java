package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter

@Entity
@ToString
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
    private int itemsId;
    @Column(nullable = false)
    private int optionsId;


    @Override
    public String toString() {
        return "id = " +id +" itemid : " + itemsId + " memberMail : " +memberMail;
    }
}