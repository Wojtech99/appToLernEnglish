package com.example.englishlerningapp.category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OrderBy;

@Getter @Setter
public class CategoryDto {
    private Long id;
    private String polishesCategory;
    private String englishesCategory;
    private String germansCategory;
}
