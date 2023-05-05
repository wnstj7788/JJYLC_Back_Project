package org.ssafy.shopping.backend.service;

import org.ssafy.shopping.backend.dto.OrderDto;

import java.util.List;

public interface OrderService {
    String orderByCarts(String Member);
    List<OrderDto> findByMemberMail(String memberMail);

}
