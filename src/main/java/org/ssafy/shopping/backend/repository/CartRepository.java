package org.ssafy.shopping.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ssafy.shopping.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberMail(String memberMail);

    void deleteById(String orderId);


    Cart findByMemberMailAndItemsId(String memberMail, int itemId);

    @Modifying
    @Transactional
    @Query(value = "delete from carts where id = :orderId", nativeQuery = true)
    int deleteCartById(@Param("orderId") int orderId);
}