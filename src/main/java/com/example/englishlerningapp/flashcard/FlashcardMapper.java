package com.example.englishlerningapp.flashcard;

import com.example.englishlerningapp.topic.Topic;
import com.example.englishlerningapp.topic.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class FlashcardMapper {
    private final TopicRepository topicRepository;

    public FlashcardMapper(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    Flashcard map(FlashcardDto dto) {
        Topic topic = topicRepository.findById(dto.getTopicId()).get();

        return Flashcard.builder()
                .id(dto.getId())
                .polishesWord(dto.getPolishesWord())
                .englishesWord(dto.getEnglishesWord())
                .germansWord(dto.getGermansWord())
                .topic(topic)
                .build();
    }

    FlashcardDto map(Flashcard flashcard) {
        FlashcardDto dto = new FlashcardDto();

        dto.setId(flashcard.getId());
        dto.setPolishesWord(flashcard.getPolishesWord());
        dto.setEnglishesWord(flashcard.getEnglishesWord());
        dto.setGermansWord(flashcard.getGermansWord());
        dto.setTopicId(flashcard.getTopic().getId());

        return dto;
    }
}
