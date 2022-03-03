package com.example.englishlerningapp.category;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    String home(Model model) {
        categoryService.findAllCategories().ifPresent(list ->
                model.addAttribute("ListOfCategories", list)
        );

        return "index";
    }

    @GetMapping("/add_category")
    String addCategory() {
        return "add_category";
    }

    @PostMapping("/add_new_category")
    String add(){
        return "";
    }
}
