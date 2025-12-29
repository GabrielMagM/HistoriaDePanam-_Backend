package gabrielmagm.historiatrivia_backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gabrielmagm.historiatrivia_backend.models.QuestionTypeModel;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionTypeModel, Long> {
    Optional<QuestionTypeModel> findByName(String name);
    boolean existsByName(String name); 
}