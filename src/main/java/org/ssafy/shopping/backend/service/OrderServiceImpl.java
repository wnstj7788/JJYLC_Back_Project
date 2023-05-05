package org.ssafy.shopping.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.shopping.backend.dto.OrderDto;
import org.ssafy.shopping.backend.entity.Cart;
import org.ssafy.shopping.backend.entity.Item;
import org.ssafy.shopping.backend.entity.Order;
import org.ssafy.shopping.backend.repository.CartRepository;
import org.ssafy.shopping.backend.repository.ItemRepository;
import org.ssafy.shopping.backend.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemRepository itemRepository;
    @Transactional
    @Override
    public String orderByCarts(String memberMail) {
        List<Cart> cartList = cartRepository.findByMemberMail(memberMail);
        try{
            for (Cart c : cartList) {
                Order order = new Order();
                order.setMemberMail(memberMail);
                order.setItemsId(c.getItemsId());
                order.setQuantity(c.getQuantity());
                // 배달 요청 사항 미 구현 하드코딩
                order.setInfo("배달 요청 사항");
                order.setOptionsId(c.getOptionsId());
                order.setOrderDate(LocalDateTime.now());
                orderRepository.save(order);
                cartRepository.deleteCartById(c.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
            return "주문 실패";
        }
        return "주문 성공";
    }

    @Override
    public List<OrderDto> findByMemberMail(String memberMail) {
        List<Order> orders = orderRepository.findByMemberMail(memberMail);
        List<OrderDto> result = new ArrayList<>();
        for (Order order:orders) {
            OrderDto o = new OrderDto();
            Item i = itemRepository.findById(order.getItemsId());
            o.setMemberMail(memberMail);
            o.setItemId(order.getItemsId());
            o.setOrderPrice(i.getPrice());
            o.setOrderDate(order.getOrderDate());
            o.setDiscountPer(i.getDiscountPer());

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime orderDate = order.getOrderDate();
            String status = "";
            if (now.isAfter(orderDate.plusDays(5))) {
                status =  "배송 완료";
            } else if (now.isAfter(orderDate.plusDays(3))) {
                status =  "배송 중";
            } else if (now.isAfter(orderDate.plusDays(1))) {
                status =  "배송 시작";
            } else {
                status =  "배송 준비 중";
            }
            o.setOrderStatus(status);
            result.add(o);
        }
        return result;
    }
}
