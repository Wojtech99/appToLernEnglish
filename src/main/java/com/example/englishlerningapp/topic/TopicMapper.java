package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Service;

@Service
public class TopicMapper {
    Topic map(TopicDto dto) {

        return Topic.builder()
                .id(dto.getId())
                .polishesTopic(dto.getPolishesTopic())
                .englishesTopic(dto.getEnglishesTopic())
                .germansTopic(dto.getGermansTopic())
                .build();
    }

    TopicDto map(Topic topic) {
        TopicDto dto = new TopicDto();

        dto.setId(topic.getId());
        dto.setPolishesTopic(topic.getPolishesTopic());
        dto.setEnglishesTopic(topic.getEnglishesTopic());
        dto.setGermansTopic(topic.getGermansTopic());
        dto.setCategoryId(topic.getCategory().getId());
        dto.setCategoryName(topic.getCategory().getPolishesCategory());

        return dto;
    }
}
