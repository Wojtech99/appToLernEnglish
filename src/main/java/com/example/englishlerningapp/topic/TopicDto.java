package com.example.englishlerningapp.topic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicDto {
    private Long id;
    private String polishesTopic;
    private String englishesTopic;
    private String germansTopic;
    private Long categoryId;
}
