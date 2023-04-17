package org.ssafy.shopping.backend.repository;

import org.ssafy.shopping.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberMail(String memberMail);

    Cart findByMemberMailAndItemId(String memberMail,int itemId);
//    void deleteByMemberId(int memberId);
}