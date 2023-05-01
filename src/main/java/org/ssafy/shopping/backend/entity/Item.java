package org.ssafy.shopping.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@Entity
@Table(name = "items")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String price;

    @Column(length = 255, nullable = true)
    private String discountPer;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private int quantity;


    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "itemUrl",fetch = FetchType.LAZY)
    private List<ItemsImages> itemUrl = new ArrayList<>();

    public Item() {
    }

    public Item(int id, String name, String price, String discountPer, String description, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt, List<ItemsImages> itemUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPer = discountPer;
        this.description = description;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.itemUrl = itemUrl;
    }
}
