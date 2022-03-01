package com.example.englishlerningapp.flashcard;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlashCardService {
    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper flashcardMapper;

    public FlashCardService(FlashcardRepository flashcardRepository, FlashcardMapper flashcardMapper) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
    }

    FlashcardDto saveFlashcard(FlashcardDto dto) {
        Flashcard flashcardToSave = flashcardMapper.map(dto);
        Flashcard savedFlashcard = flashcardRepository.save(flashcardToSave);

        return flashcardMapper.map(savedFlashcard);
    }

    Optional<FlashcardDto> replaceFlashcard(Long id, FlashcardDto flashcardDto) {
        if (!flashcardRepository.existsById(id)){
            return Optional.empty();
        }

        flashcardDto.setId(id);
        Flashcard flashcardToReplace = flashcardMapper.map(flashcardDto);
        Flashcard replacedFlashcard = flashcardRepository.save(flashcardToReplace);

        return Optional.of(flashcardMapper.map(replacedFlashcard));
    }

    void deleteFlashcard(Long id) {
        flashcardRepository.deleteById(id);
    }

    Optional<FlashcardDto> updateFlashcard(Long id, FlashcardDto flashcardDto) {
        return flashcardRepository.findById(id)
                .map(target -> setEntityFields(flashcardDto, target))
                .map(updatedFlashcard -> flashcardMapper.map(updatedFlashcard));
    }

    private Flashcard setEntityFields(FlashcardDto source, Flashcard target) {
        if (source.getPolishesWord() != null) {
            target.setPolishesWord(source.getPolishesWord());
        }
        if (source.getEnglishesWord() != null) {
            target.setEnglishesWord(source.getEnglishesWord());
        }
        if (source.getGermansWord() != null) {
            target.setGermansWord(source.getGermansWord());
        }

        return target;
    }
}
