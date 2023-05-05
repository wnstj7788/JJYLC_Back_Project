package org.ssafy.shopping.backend.service;

import org.ssafy.shopping.backend.dto.CartDto;
import org.ssafy.shopping.backend.entity.Cart;

import java.util.List;

public interface CartService {
    List<CartDto> findItemsByMemberMail(String memberMail);
    String deleteCart(int orderId);
    void insertCart(String memberMail, int itemId,int quantity) throws Exception;

}