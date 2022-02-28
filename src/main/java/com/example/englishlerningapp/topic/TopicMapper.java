package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Service;

@Service
public class TopicMapper {
    Topic map(TopicDto dto) {
        Topic topic = Topic.builder()
                .polishesTopic(dto.getPolishesTopic())
                .englishesTopic(dto.getEnglishesTopic())
                .germansTopic(dto.getGermansTopic())
                .build();

        return topic;
    }

    TopicDto map(Topic topic) {
        TopicDto dto = new TopicDto();

        dto.setPolishesTopic(topic.getPolishesTopic());
        dto.setEnglishesTopic(topic.getEnglishesTopic());
        dto.setGermansTopic(topic.getGermansTopic());

        return dto;
    }
}
