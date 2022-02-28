package com.example.englishlerningapp.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    Category map(CategoryDto dto) {
        Category category = Category.builder()
                .polishesCategory(dto.getPolishesCategory())
                .englishesCategory(dto.getEnglishesCategory())
                .germansCategory(dto.getGermansCategory())
                .build();

        return category;
    }

    CategoryDto map(Category category) {
        CategoryDto dto = new CategoryDto();

        dto.setPolishesCategory(category.getPolishesCategory());
        dto.setEnglishesCategory(category.getEnglishesCategory());
        dto.setGermansCategory(category.getGermansCategory());

        return dto;
    }
}
