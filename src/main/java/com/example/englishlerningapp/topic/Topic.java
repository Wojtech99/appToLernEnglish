package com.example.englishlerningapp.topic;

import com.example.englishlerningapp.category.Category;
import com.example.englishlerningapp.flashcard.Flashcard;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    private String polishesTopic;
    @NotNull
    @Size(min = 2, max = 100)
    private String englishesTopic;
    private String germansTopic;
    @OneToMany(mappedBy = "topic")
    private List<Flashcard> flashcards = new ArrayList<>();
    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private Category category;

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }
}
