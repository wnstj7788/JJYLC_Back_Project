package org.ssafy.shopping.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssafy.shopping.backend.dto.ItemDto;
import org.ssafy.shopping.backend.entity.Item;
import org.ssafy.shopping.backend.entity.ItemsImages;
import org.ssafy.shopping.backend.repository.ItemRepository;
import org.ssafy.shopping.backend.repository.ItemsImagesRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemsImagesRepository itemsImagesRepository;

    @Override
    public List<ItemDto> selectAllItems() {
        
        List<Item> items = itemRepository.findAll();

        List<ItemDto> result = new ArrayList<>();

        for (Item i : items) {
            
            List<ItemsImages> itemImages = itemsImagesRepository.findByItemsId(i.getId());

            ItemDto itemDto = new ItemDto();
            
            itemDto.setId(i.getId());
            itemDto.setName(i.getName());
            itemDto.setPrice(i.getPrice());
            itemDto.setImgPath(itemImages.get(0).getImageUrl());

            result.add(itemDto);
        }

        return result;
    }


    
    
}
