package com.example.englishlerningapp.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    CategoryDto saveCategory(CategoryDto dto) {
        Category CategoryToSave = categoryMapper.map(dto);
        Category savedCategory = categoryRepository.save(CategoryToSave);

        return categoryMapper.map(savedCategory);
    }

    Optional<CategoryDto> replaceCategory(Long CategoryId, CategoryDto category) {
        if (!categoryRepository.existsById(CategoryId)){
            return Optional.empty();
        }

        category.setId(CategoryId);
        Category categoryToReplace = categoryMapper.map(category);
        Category replacedCategory = categoryRepository.save(categoryToReplace);

        return Optional.of(categoryMapper.map(replacedCategory));
    }

    void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    Optional<CategoryDto> updateCategory(Long id, CategoryDto categoryDto) {
        return categoryRepository.findById(id)
                .map(target -> setEntityFields(categoryDto, target))
                .map(categoryMapper::map);
    }

    private Category setEntityFields(CategoryDto source, Category target) {
        if (source.getPolishesCategory() != null) {
            target.setPolishesCategory(source.getPolishesCategory());
        }
        if (source.getEnglishesCategory() != null) {
            target.setEnglishesCategory(source.getEnglishesCategory());
        }
        if (source.getGermansCategory() != null) {
            target.setGermansCategory(source.getGermansCategory());
        }
        return target;
    }
}
