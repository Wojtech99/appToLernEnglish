package com.example.englishlerningapp.flashcard;

import com.example.englishlerningapp.topic.Topic;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String polishesWord;
    private String englishesWord;
    private String germansWord;
    private Date dateAdded;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
