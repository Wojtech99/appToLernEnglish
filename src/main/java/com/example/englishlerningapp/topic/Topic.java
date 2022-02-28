package com.example.englishlerningapp.topic;

import com.example.englishlerningapp.category.Category;
import com.example.englishlerningapp.flashcard.Flashcard;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String polishesTopic;
    private String englishesTopic;
    private String GermansTopic;
    @OneToMany(mappedBy = "topic")
    private List<Flashcard> flashcards = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;
}