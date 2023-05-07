package org.ssafy.shopping.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.shopping.backend.entity.ItemsImages;

public interface ItemsImagesRepository extends JpaRepository<ItemsImages, Integer> {
    List<ItemsImages> findByItemsId(int itemId);
}