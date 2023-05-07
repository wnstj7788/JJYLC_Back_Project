package org.ssafy.shopping.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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

    @Column(length = 50, nullable = false)
    private String gallery_number;
    
    @Column(nullable = false)
    private String price;

    @Column(length = 255, nullable = true)
    private String discountPer;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String features;

    @Column(length = 50,nullable = true)
    private String sizeInfo;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int itemsCategoryId;

    @JsonIgnore
    @OneToMany(mappedBy = "itemUrl", fetch = FetchType.LAZY)
    private List<ItemsImages> itemUrl = new ArrayList<>();

    public Item() {

    }

    public Item(int id, String name, String gallery_number, String price, String discountPer, String description,
            String features, String sizeInfo, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt,
            int itemsCategoryId, List<ItemsImages> itemUrl) {
        this.id = id;
        this.name = name;
        this.gallery_number = gallery_number;
        this.price = price;
        this.discountPer = discountPer;
        this.description = description;
        this.features = features;
        this.sizeInfo = sizeInfo;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.itemsCategoryId = itemsCategoryId;
        this.itemUrl = itemUrl;
    }

    
}
