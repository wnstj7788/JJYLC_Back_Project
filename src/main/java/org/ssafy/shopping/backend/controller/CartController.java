package org.ssafy.shopping.backend.controller;

import org.ssafy.shopping.backend.dto.CartDto;
import org.ssafy.shopping.backend.entity.*;
import org.ssafy.shopping.backend.repository.*;
import org.ssafy.shopping.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        String memberMail = jwtService.getMemberMail(token);
        List<CartDto> cartList = cartService.findItemsByMemberMail(memberMail);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }
    
    // quantity 미구현으로 인한 하드코딩
    //    @PostMapping("/api/cart/items/{itemId}/{quantity}")
    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
//  quantity 구현 시 가능
//            @PathVariable("quantity") int quantity,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        try{
            // quantity 미구현 하드코딩
//            cartService.insertCart(memberMail,itemId,quantity);
            cartService.insertCart(memberMail,itemId,2);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("아이템 입력 실패",HttpStatus.OK);
        }


        return new ResponseEntity<>("아이템 입력 성공",HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{orderId}")
    public ResponseEntity removeCartItem(
            @PathVariable("orderId") int orderId,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        String result = cartService.deleteCart(orderId);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}