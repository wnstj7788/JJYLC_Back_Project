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
    OrderService orderService;
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
        List<OrderDto> orderlist = orderService.findByMemberMail(memberMail);
        return new ResponseEntity<>(orderlist, HttpStatus.OK);
    }

    @GetMapping("/api/order")
    public ResponseEntity pushOrder(
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        String memberMail = jwtService.getMemberMail(token);
        String result = orderService.orderByCarts(memberMail);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}