package com.example.englishlerningapp.flashcard;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class FlashcardDto {
    private Long id;
    @NotNull(message = "to pole nie może być puste")
    @Size(min = 2, max = 100, message = "to pole musi mieć od 2 do 100 znaków")
    private String polishesWord;
    @NotNull
    @Size(min = 2, max = 100)
    private String englishesWord;
    private String germansWord;
    @Min(1)
    private Long TopicId;
}
