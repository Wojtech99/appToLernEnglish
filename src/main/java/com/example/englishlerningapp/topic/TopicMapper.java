package com.example.englishlerningapp.topic;


import com.example.englishlerningapp.category.Category;
import com.example.englishlerningapp.category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicMapper {

    private final CategoryRepository categoryRepository;

    public TopicMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Topic map(TopicDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId()).get();

        return Topic.builder()
                .id(dto.getId())
                .polishesTopic(dto.getPolishesTopic())
                .englishesTopic(dto.getEnglishesTopic())
                .germansTopic(dto.getGermansTopic())
                .category(category)
                .build();
    }

    TopicDto map(Topic topic) {
        TopicDto dto = new TopicDto();

        dto.setId(topic.getId());
        dto.setPolishesTopic(topic.getPolishesTopic());
        dto.setEnglishesTopic(topic.getEnglishesTopic());
        dto.setGermansTopic(topic.getGermansTopic());
        dto.setCategoryId(topic.getCategory().getId());

        return dto;
    }
}
