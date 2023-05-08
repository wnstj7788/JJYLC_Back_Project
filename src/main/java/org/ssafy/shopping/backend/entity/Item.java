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

    @Column(nullable = false, unique = true,length = 10)
    private String itemsCategoryId;

    @JsonIgnore
    @OneToMany(mappedBy = "itemUrl", fetch = FetchType.LAZY)
    private List<ItemsImages> itemUrl = new ArrayList<>();

}
