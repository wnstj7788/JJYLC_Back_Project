package org.ssafy.shopping.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssafy.shopping.backend.dto.CartDto;
import org.ssafy.shopping.backend.entity.Cart;
import org.ssafy.shopping.backend.entity.Item;
import org.ssafy.shopping.backend.repository.CartRepository;
import org.ssafy.shopping.backend.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public List<CartDto> findItemsByMemberMail(String memberMail) {
        List<Cart> carts = cartRepository.findByMemberMail(memberMail);
        List<CartDto> cartList =new ArrayList<>();
        for (Cart c: carts) {
            // 뿌릴 데이터
            Item i = itemRepository.findById(c.getItemsId());
            CartDto cartDto = new CartDto();
            cartDto.setId(c.getId());
            cartDto.setItemId(c.getItemsId());
            cartDto.setName(i.getName());
            cartDto.setDiscountPer(i.getDiscountPer());
            cartDto.setPrice(i.getPrice());
            if(i.getItemUrl().size()==0 ){
                cartDto.setImgPath("");
            }else{
                cartDto.setImgPath(String.valueOf(i.getItemUrl().get(0)));
            }
            cartDto.setQuantity(c.getQuantity());
            cartList.add(cartDto);
        }
        return cartList;
    }

    @Override
    public String deleteCart(int orderId) {
        Optional<Cart> cartOptional = cartRepository.findById(orderId);
        String result = "";
        if(cartOptional.isPresent()){
            int removeResult = cartRepository.deleteCartById(orderId);
            result = removeResult>0 ? "삭제 성공": "삭제 실패";
        }
        return result;
    }

    @Override
    public void insertCart(String memberMail, int itemId, int quantity) throws Exception {
        Cart cart = cartRepository.findByMemberMailAndItemsId(memberMail, itemId);
        if (cart == null) {
            cart = new Cart();
            cart.setMemberMail(memberMail);
            cart.setItemsId(itemId);
            cart.setQuantity(quantity);
            // option 미구현으로 인한 하드코딩
            cart.setOptionsId(1);
        }else{
            cart.setQuantity(quantity);
        }
        System.out.println(cart.toString());
        cartRepository.save(cart);
    }
}
