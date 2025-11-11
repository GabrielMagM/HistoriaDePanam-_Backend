package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;
import gabrielmagm.historiatrivia_backend.models.TopicModel;

public interface TopicService {
    List<TopicModel> getAllTopics();
    List<TopicModel> getTopicsBySection(Long sectionId);
    TopicModel getTopicById(Long id);
    TopicModel createTopic(TopicModel topic);
    TopicModel updateTopic(Long id, TopicModel topic);
    Boolean deleteTopic(Long id);
}