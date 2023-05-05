package org.ssafy.shopping.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {

    private int id;
    private String memberMail;
    private int itemId;
    private String name;
    private int quantity;
    private String discountPer;
    private String price;
    private String imgPath;
}