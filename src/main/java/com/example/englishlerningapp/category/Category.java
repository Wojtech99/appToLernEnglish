package com.example.englishlerningapp.category;

import com.example.englishlerningapp.topic.Topic;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    private String polishesCategory;
    @NotNull
    @Size(min = 2, max = 100)
    private String englishesCategory;
    private String germansCategory;
    @OneToMany(mappedBy = "category")
    private List<Topic> topics = new ArrayList<>();

    public void addTopic(Topic topic) {
        topics.add(topic);
    }
}
