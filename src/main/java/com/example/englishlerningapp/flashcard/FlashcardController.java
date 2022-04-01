package com.example.englishlerningapp.flashcard;

import com.example.englishlerningapp.topic.TopicDto;
import com.example.englishlerningapp.topic.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FlashcardController {
    private final FlashcardService flashcardService;
    private final TopicService topicService;

    public FlashcardController(FlashcardService flashcardService, TopicService topicService) {
        this.flashcardService = flashcardService;
        this.topicService = topicService;
    }

    //go to adding flashcard site
    @GetMapping("/addFlashcard")
    String addFlashcardPage(Model model) {
        List<TopicDto> dtoList = topicService.takeAllTopics();

        model.addAttribute("flashcard", new FlashcardDto());
        model.addAttribute("topics", dtoList);

        return "add_flashcard";
    }

    //add flashcard
    @PostMapping("/addNewFlashcard")
    String addFlashcard(@Valid @ModelAttribute("flashcard") FlashcardDto flashcardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "add_flashcard";
        }

        flashcardService.saveFlashcard(flashcardDto);
        return "redirect:/addFlashcard";
    }

    //show all flashcards
    @RequestMapping(
            value = "/showFlashcards",
            method = {RequestMethod.GET}
    )
    String showAllFlashcards(Model model) {
        List<FlashcardDto> flashcardDtoList = flashcardService.takeAllFlashcards().get();

        model.addAttribute("flashcardsList", flashcardDtoList);

        return "show_flashcards";
    }

    //delete flashcard
    @RequestMapping(
            value = "/showFlashcards/delete/{id}",
            method = {RequestMethod.DELETE, RequestMethod.GET}
    )
    String deleteFlashcard(@PathVariable Long id) {
        flashcardService.deleteFlashcard(id);

        return "redirect:/showFlashcards";
    }

    //go to edit flashcard page
    @GetMapping("/showFlashcards/edit/{id}")
    String goToEditSite(@PathVariable("id") Long id, Model model) {
        FlashcardDto flashcardDto = flashcardService.takeFlashcard(id).get();

        model.addAttribute("flashcard", flashcardDto);

        return "edit_flashcard";
    }

    //edit flashcard
    @RequestMapping(
            value = "/editFlashcard",
            method = {RequestMethod.PATCH, RequestMethod.POST}
    )
    String editFlashcard(@Valid @ModelAttribute("flashcard") FlashcardDto flashcardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_flashcard";
        }

        flashcardService.updateFlashcard(flashcardDto);
        return "redirect:/showFlashcards";
    }

}
