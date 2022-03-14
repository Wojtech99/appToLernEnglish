package com.example.englishlerningapp.flashcard;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlashcardDto {
    private Long id;
    private String polishesWord;
    private String englishesWord;
    private String germansWord;
    private Long TopicId;
}
