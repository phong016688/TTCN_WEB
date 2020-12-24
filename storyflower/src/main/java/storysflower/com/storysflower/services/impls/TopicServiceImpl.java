package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.TopicDTO;
import storysflower.com.storysflower.repositories.TopicRepository;
import storysflower.com.storysflower.services.TopicService;

import java.util.List;
@Component
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository  topicRepository;

    @Override
    public List<TopicDTO> findAllTopic() {
        return topicRepository.getAllTopic();
    }

}
