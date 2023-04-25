package org.ssafy.shopping.backend.repository;

import org.ssafy.shopping.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findById(int itemId);
    List<Item> findByIdIn(List<Integer> ids);
}