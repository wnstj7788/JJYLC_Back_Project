package org.ssafy.shopping.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private int id;
    private String memberMail;
    private int itemId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private String discountPer;
    private String orderPrice;

    private String itemName;
    private String imgPath;
}