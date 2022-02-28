package com.example.englishlerningapp.flashcard;

import org.springframework.stereotype.Service;

@Service
public class FlashCardService {
    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper flashcardMapper;

    public FlashCardService(FlashcardRepository flashcardRepository, FlashcardMapper flashcardMapper) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
    }
}
