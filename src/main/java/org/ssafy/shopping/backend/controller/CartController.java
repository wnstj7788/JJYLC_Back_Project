package org.ssafy.shopping.backend.controller;

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
    ItemRepository itemRepository;

    @GetMapping("/api/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token", required = false) String token) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        List<Cart> carts = cartRepository.findByMemberMail(memberMail);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
        List<Item> items = itemRepository.findByIdIn(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        Cart cart = cartRepository.findByMemberMailAndItemId(memberMail, itemId);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setMemberMail(memberMail);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token", required = false) String token
    ) {

        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String memberMail = jwtService.getMemberMail(token);
        Cart cart = cartRepository.findByMemberMailAndItemId(memberMail, itemId);

        cartRepository.delete(cart);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}