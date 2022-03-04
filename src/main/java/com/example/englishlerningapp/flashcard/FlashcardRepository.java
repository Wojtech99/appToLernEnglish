package com.example.englishlerningapp.flashcard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
}
