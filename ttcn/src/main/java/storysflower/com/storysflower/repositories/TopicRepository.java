package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.TopicDTO;

import static storysflower.com.storysflower.model.tables.tables.Topic.TOPIC;

import java.util.Collections;
import java.util.List;

@Repository
public class TopicRepository {
    @Autowired
    private DSLContext dslContext;

    public List<TopicDTO> getAllTopic(){
        List<TopicDTO> listTopic = dslContext
                .select(TOPIC.TOPIC_ID.as("idTopic"), TOPIC.TOPIC_NAME.as("nameTopic"))
                .from(TOPIC)
                .fetchInto(TopicDTO.class);
        return  listTopic.size()==0? Collections.emptyList() : listTopic;
    }
}
