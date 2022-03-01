package com.example.englishlerningapp.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    Category map(CategoryDto dto) {

        return Category.builder()
                .id(dto.getId())
                .polishesCategory(dto.getPolishesCategory())
                .englishesCategory(dto.getEnglishesCategory())
                .germansCategory(dto.getGermansCategory())
                .build();
    }

    CategoryDto map(Category category) {
        CategoryDto dto = new CategoryDto();

        dto.setId(category.getId());
        dto.setPolishesCategory(category.getPolishesCategory());
        dto.setEnglishesCategory(category.getEnglishesCategory());
        dto.setGermansCategory(category.getGermansCategory());

        return dto;
    }
}
