package org.ssafy.shopping.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "items_images")
public class ItemsImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(length = 255, nullable = false)
    private String imageUrl;

    @Column(nullable = false,name="items_id", insertable = false, updatable = false)
    private int itemsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id", referencedColumnName = "id")
    private Item itemUrl;

    public ItemsImages() {
    }

    public ItemsImages(int id, String imageUrl, int itemsId, Item itemUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.itemsId = itemsId;
        this.itemUrl = itemUrl;
    }
}
