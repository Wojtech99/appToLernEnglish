package com.example.englishlerningapp.topic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    Optional<TopicDto> updateTopic(Long id, TopicDto topicDto) {
        return topicRepository.findById(id)
                .map(target -> setEntityFields(topicDto, target))
                .map(updatedTopic -> topicMapper.map(updatedTopic));
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
}
