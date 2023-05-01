package org.ssafy.shopping.backend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.shopping.backend.entity.Item;
import org.ssafy.shopping.backend.repository.ItemRepository;

import org.springframework.util.StringUtils;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/search")
    public List<String> getItemListByComplete(@RequestParam("searchName") String searchName) {
        searchName = searchName.replace('+', '|');
        List<Item> temp = itemRepository.searchByRegExp(searchName);
        List<String> nameList = new ArrayList<>();
        for (Item item : temp) {
            nameList.add(item.getName());
            // System.out.println(item.getName());
        }
        return nameList;
    }

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }


    @PostMapping("/api/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
            @RequestParam("price") String price, @RequestParam("discountPer") String discountPer,
            @RequestParam("tag") int tag) {
        try {
            // 파일 저장 디렉토리 경로 설정

            System.out.println(tag);
            // 파일 이름
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // 파일 저장 경로 생성
            String uploadDir = "src/main/resources/static/uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 저장 경로와 파일 이름 결합
            Path filePath = uploadPath.resolve(fileName);

            // 파일 저장
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Item item = new Item();
            item.setName(fileName);
            String uploadDBpath = "uploads/";
//            item.setImgPath(uploadDBpath + fileName);
            item.setDiscountPer(discountPer);
            item.setPrice(price);
            itemRepository.save(item);

            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Failed to upload file.";
    }
}
