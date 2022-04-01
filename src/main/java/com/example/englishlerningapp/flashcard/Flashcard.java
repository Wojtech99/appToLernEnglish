package com.example.englishlerningapp.flashcard;

import com.example.englishlerningapp.topic.Topic;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size
    private String polishesWord;
    @NotNull
    @Size
    private String englishesWord;
    private String germansWord;
    private Date dateAdded;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
