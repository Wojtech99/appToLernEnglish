package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    TopicDto saveTopic(TopicDto dto) {
        Topic topicToSave = topicMapper.map(dto);
        Topic savedTopic = topicRepository.save(topicToSave);

        return topicMapper.map(savedTopic);
    }

    Optional<TopicDto> replaceTopic(Long topicId, TopicDto dto) {
        if (!topicRepository.existsById(topicId)){
            return Optional.empty();
        }
        dto.setId(topicId);
        Topic topicToSave = topicMapper.map(dto);
        Topic updatedTopic = topicRepository.save(topicToSave);

        return Optional.of(topicMapper.map(updatedTopic));
    }

    void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    Optional<TopicDto> updateOrSaveTopic(TopicDto topicDto) {
        if (topicDto.getId() == null) {
            TopicDto savedTopic = saveTopic(topicDto);
            return Optional.of(savedTopic);
        }

        Topic topic = topicRepository.findById(topicDto.getId()).get();
        Topic topicToUpdate =  setEntityFields(topicDto, topic);
        Topic updatedTopic = topicRepository.save(topicToUpdate);

        return Optional.of(topicMapper.map(updatedTopic));
    }

    private Topic setEntityFields(TopicDto source, Topic target) {
        if (source.getPolishesTopic() != null) {
            target.setPolishesTopic(source.getPolishesTopic());
        }
        if (source.getEnglishesTopic() != null) {
            target.setEnglishesTopic(source.getEnglishesTopic());
        }
        if (source.getGermansTopic() != null) {
            target.setGermansTopic(source.getGermansTopic());
        }
        return target;
    }

    public Optional<List<TopicDto>> takeAllTopics() {
        List<TopicDto> dtoList = new ArrayList<>();

        topicRepository.findAll().forEach(topic ->
                dtoList.add(topicMapper.map(topic))
                );

        return Optional.of(dtoList);
    }

    Optional<TopicDto> takeTopic(Long id) {
        if (!topicRepository.existsById(id)){
            return Optional.empty();
        }

        Topic topic = topicRepository.findById(id).get();
        return Optional.of(topicMapper.map(topic));
    }
}
