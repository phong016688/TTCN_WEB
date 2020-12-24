package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.TopicDTO;

import java.util.List;

public interface TopicService {
    List<TopicDTO> findAllTopic();
}
