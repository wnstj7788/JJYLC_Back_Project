package org.ssafy.shopping.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.shopping.backend.entity.Item;
import org.ssafy.shopping.backend.entity.ItemsImages;

public interface ItemsImagesRepository extends JpaRepository<ItemsImages, Integer> {
    Item findByItemsId(int itemId);
}