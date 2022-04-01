package com.example.englishlerningapp.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter @Setter
public class CategoryDto {
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    private String polishesCategory;
    @NotNull
    @Size(min = 2, max = 100)
    private String englishesCategory;
    private String germansCategory;
}
