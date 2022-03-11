package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
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
        model.addAttribute("topic", new TopicDto());

        return "add_topic";
    }

    @PostMapping("/saveNewTopic")
    String addTopic(TopicDto topicDto) {
        topicService.saveTopic(topicDto);

        return "redirect:/showTopics";
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

        return "edit_topic";
    }

    @RequestMapping(
            value = "/showTopics/edit",
            method = {RequestMethod.PATCH, RequestMethod.POST}
    )
    String editTopic(TopicDto topicDto) {
        topicService.updateTopic(topicDto.getId(), topicDto);

        return "redirect:/showTopics";
    }
}
