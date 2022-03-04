package com.example.englishlerningapp.category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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

    //Add Category
    @GetMapping("/add_category")
    String addCategory(Model model) {
        model.addAttribute("category", new CategoryDto());

        return "add_category";
    }

    @PostMapping("/add_new_category")
    String saveCategory(CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);

        return "redirect:/add_category";
    }


    //Show all categories
    @GetMapping("/show_categories")
    String showCategories(Model model) {
        categoryService.findAllCategories().ifPresent(list ->
                model.addAttribute("listOfCategories", list)
        );

        return "show_categories";
    }

    //Delete category
    @RequestMapping(
            value = "/show_categories/delete/{id}",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    String deleteCategory(@PathVariable(name = "id") Long categoryId){
        categoryService.deleteCategory(categoryId);

        return "redirect:/show_categories";
    }

    //Edit category
    @RequestMapping(
            path = "/show_categories/edit/{id}",
            method =  {RequestMethod.GET, RequestMethod.POST})
    String goToEditCategory(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findCategoryById(id).get();
        categoryDto.setId(id);


        return "redirect:/edit_category";

    }

    @GetMapping("/edit_category")
    String editCategorySite(CategoryDto categoryDto, Model model) {
        model.addAttribute("category", categoryDto);

        return "edit_category";
    }


    @RequestMapping(
            value = "/show_categories/edit",
            method = {RequestMethod.PATCH, RequestMethod.POST})
    String editCategory( CategoryDto category) {
        categoryService.updateCategory(category.getId(), category);

        return "redirect:/show_categories";
    }
}

