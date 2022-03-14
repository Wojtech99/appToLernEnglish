package com.example.englishlerningapp.flashcard;

import com.example.englishlerningapp.topic.TopicDto;
import com.example.englishlerningapp.topic.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FlashcardController {
    private final FlashCardService flashCardService;
    private final TopicService topicService;

    public FlashcardController(FlashCardService flashCardService, TopicService topicService) {
        this.flashCardService = flashCardService;
        this.topicService = topicService;
    }

    @GetMapping("/addFlashcard")
    String addFlashcardPage(Model model) {
        List<TopicDto> dtoList = topicService.takeAllTopics();

        model.addAttribute("flashcard", new FlashcardDto());
        model.addAttribute("topics", dtoList);

        return "add_flashcard";
    }

    @PostMapping("/addNewFlashcard")
    String addFlashcard(FlashcardDto flashcardDto) {
        flashCardService.saveFlashcard(flashcardDto);

        return "redirect:/addFlashcard";
    }
}
