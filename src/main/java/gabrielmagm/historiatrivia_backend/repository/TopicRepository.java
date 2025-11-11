package gabrielmagm.historiatrivia_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gabrielmagm.historiatrivia_backend.models.TopicModel;

@Repository
public interface TopicRepository extends JpaRepository<TopicModel, Long> {
    List<TopicModel> findBySectionId(Long sectionId);
    boolean existsByTitleAndSectionId(String title, Long sectionId);
}