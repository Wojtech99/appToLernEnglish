package com.example.englishlerningapp.topic;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class TopicDto {
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    private String polishesTopic;
    @NotNull
    @Size(min = 2, max = 100)
    private String englishesTopic;
    private String germansTopic;
    @Min(1)
    private Long categoryId;
}
