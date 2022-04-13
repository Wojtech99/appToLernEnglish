package com.example.englishlerningapp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resource-topic")
public class TopicResource {

    private final TopicService topicService;

    public TopicResource(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    ResponseEntity<TopicDto> getTopicById(@Valid @PathVariable Long id) {
        return topicService.takeTopic(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all-topics")
    ResponseEntity<List<TopicDto>> getAllTopics() {
        return topicService.takeAllTopics()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save-topic")
    ResponseEntity<TopicDto> saveTopic(@Valid @RequestBody TopicDto topicDto) {
        TopicDto savedTopic = topicService.saveTopic(topicDto);

        URI savedTopicUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTopic.getId())
                .toUri();

        return ResponseEntity.created(savedTopicUri).body(savedTopic);
    }

    //ta sama sytuacaja co w klasie flashcard resource
    @PatchMapping("/update-topic")
    ResponseEntity<?> updateTopic(@Valid @RequestBody TopicDto topicDto) {
        return topicService.updateOrSaveTopic(topicDto)
                .map(topic -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTopic(@Valid @PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

}
