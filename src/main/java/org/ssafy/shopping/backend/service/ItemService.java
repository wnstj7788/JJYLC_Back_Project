package org.ssafy.shopping.backend.service;

import java.util.List;

import org.ssafy.shopping.backend.dto.ItemDto;

public interface ItemService {
    
    List<ItemDto> selectAllItems();

}
