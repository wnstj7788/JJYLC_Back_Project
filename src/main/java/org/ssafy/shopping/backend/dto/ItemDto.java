package org.ssafy.shopping.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    
    private int id;
    private String name;
    private String description;
    private int quantity;
    private String discountPer;
    private String price;
    private String imgPath;

}
