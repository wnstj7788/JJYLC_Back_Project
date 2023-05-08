package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items_category")
public class ItemsCategory {
    @Id
    @Column(nullable = false, unique = true,length = 10)
    private String id;

    @Column(length = 45, nullable = false)
    private String categoryName;

}
