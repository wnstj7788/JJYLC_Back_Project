package org.ssafy.shopping.backend.repository;

import org.ssafy.shopping.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findById(int itemId);

    List<Item> findByIdIn(List<Integer> ids);

    @Query(value = "select i.id, i.name, i.price, i.discount_per, i.img_path from items i where i.name REGEXP :searchKeyword ", nativeQuery = true)
    List<Item> searchByRegExp(@Param("searchKeyword") String searchKeyword);

}