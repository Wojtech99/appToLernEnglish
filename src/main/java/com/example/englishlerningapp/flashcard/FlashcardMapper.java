package com.example.englishlerningapp.flashcard;

import org.springframework.stereotype.Service;

@Service
public class FlashcardMapper {
    Flashcard map(FlashcardDto dto) {
        Flashcard flashcard = Flashcard.builder()
                .polishesWord(dto.getPolishesWord())
                .englishesWord(dto.getEnglishesWord())
                .germansWord(dto.getGermansWord())
                .build();

        return flashcard;
    }

    FlashcardDto map(Flashcard flashcard) {
        FlashcardDto dto = new FlashcardDto();

        dto.setPolishesWord(flashcard.getPolishesWord());
        dto.setEnglishesWord(flashcard.getEnglishesWord());
        dto.setGermansWord(flashcard.getGermansWord());

        return dto;
    }
}
