package gabrielmagm.historiatrivia_backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
    List<ThemeModel> findBySectionId(Long sectionId);
    ThemeModel findByTitle(String title);
    boolean existsByTitleAndSectionId(String title, Long sectionId);
}