package com.example.englishlerningapp.topic;

import com.example.englishlerningapp.category.CategoryDto;
import com.example.englishlerningapp.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TopicController {

    private final TopicService topicService;
    private final CategoryService categoryService;

    public TopicController(TopicService topicService, CategoryService categoryService, CategoryService categoryService1) {
        this.topicService = topicService;
        this.categoryService = categoryService1;
    }

    @GetMapping("/showTopics")
    String showTopics(Model model) {
        List<TopicDto> listOfTopics = topicService.takeAllTopics();

        model.addAttribute("listOfTopics", listOfTopics);

        return "show_topics";
    }

    //add new topic
    @GetMapping("/add_topic")
    String goToAddTopic(Model model) {
        List<CategoryDto> listOfCategories = categoryService.takeAllCategories().get();

        model.addAttribute("listOfCategories", listOfCategories);
        model.addAttribute("topic", new TopicDto());

        return "add_topic";
    }

    @PostMapping("/saveNewTopic")
    String addTopic(TopicDto topicDto) {
        topicService.saveTopic(topicDto);

        return "redirect:/add_topic";
    }

    //delete topic
    @RequestMapping(
            value = "/showTopics/delete/{id}",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    String deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return "redirect:/showTopics";
    }


    @RequestMapping(
            value = "/showTopics/edit/{id}",
            method = {RequestMethod.GET})
    String goToEditTopic(@PathVariable Long id, Model model) {
        TopicDto topicDto = topicService.takeTopic(id).get();

        model.addAttribute("topic", topicDto);

        return "add_topic";
    }

    @RequestMapping(
            value = "/showTopics/edit",
            method = {RequestMethod.PATCH, RequestMethod.POST}
    )
    String editTopic(TopicDto topicDto) {
        topicService.updateOrSaveTopic(topicDto);

        return "redirect:/showTopics";
    }
}