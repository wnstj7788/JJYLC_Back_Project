package org.ssafy.shopping.backend.controller;

import lombok.Getter;
import org.ssafy.shopping.backend.dto.*;
import org.ssafy.shopping.backend.entity.*;
import org.ssafy.shopping.backend.repository.*;
import org.ssafy.shopping.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    JwtService jwtService;

    @GetMapping("/api/orderList")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        List<Order> orders = orderRepository.findByMemberMail(memberMail);
        List<OrderDto> orderlist = new ArrayList<>();
        Long currentTime = System.currentTimeMillis();
//        for (Order o : orders) {
//            System.out.println(1);
//            OrderDto dto = new OrderDto();
//            dto.setMemberMail(memberMail);
//            dto.setItemName(o.getItemName());
//            dto.setOrderDate(o.getOrderDate().toString());
//
//            Long time = o.getOrderDate().getTime();
//            if(currentTime - time >= 432000000L){ // 5일 후
//                dto.setOrderStatus("배송 완료");
//            } else if (currentTime - time >= 17280000L) {  // 2일 후
//                dto.setOrderStatus("배송 중");
//
//            } else if (currentTime - time >= 86400000L) { // 1일 후
//                dto.setOrderStatus("배송 시작");
//            }else{  // 1일 이내
//                dto.setOrderStatus("주문 완료");
//            }
//            dto.setOrderPrice(o.getOrderPrice());
//            dto.setDiscountPer(o.getDiscountPer());
//            dto.setOrderImg(o.getImg_path());
//            orderlist.add(dto);
//        }
        return new ResponseEntity<>(orderlist, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/api/order")
    public ResponseEntity pushOrder(
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

//        String memberMail = jwtService.getMemberMail(token);
//        List<Cart> cartList = cartRepository.findByMemberMail(memberMail);
//
//        for (Cart c: cartList) {
//            System.out.println("memberMail :" + memberMail);
//            System.out.println(c.toString());
//            Order newOrder = new Order();
//            newOrder.setMemberMail(memberMail);
//            newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
//            Item item = itemRepository.findById(c.getItemId());
//            newOrder.setOrderPrice(item.getPrice());
//            newOrder.setDiscountPer(item.getDiscountPer());
//            newOrder.setImg_path(item.getImgPath());
//            newOrder.setItemName(item.getName());
//            orderRepository.save(newOrder);
//        }
//        cartRepository.deleteByMemberMail(memberMail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}