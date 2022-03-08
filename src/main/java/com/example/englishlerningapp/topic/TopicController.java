package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    @GetMapping("/add_topic")
    String goToAddTopic(Model model) {
        model.addAttribute("topic", new TopicDto());

        return "add_topic";
    }
}
