package com.example.englishlerningapp.flashcard;

import org.springframework.stereotype.Service;

@Service
public class FlashcardMapper {
    Flashcard map(FlashcardDto dto) {

        return Flashcard.builder()
                .id(dto.getId())
                .polishesWord(dto.getPolishesWord())
                .englishesWord(dto.getEnglishesWord())
                .germansWord(dto.getGermansWord())
                .build();
    }

    FlashcardDto map(Flashcard flashcard) {
        FlashcardDto dto = new FlashcardDto();

        dto.setId(flashcard.getId());
        dto.setPolishesWord(flashcard.getPolishesWord());
        dto.setEnglishesWord(flashcard.getEnglishesWord());
        dto.setGermansWord(flashcard.getGermansWord());

        return dto;
    }
}
