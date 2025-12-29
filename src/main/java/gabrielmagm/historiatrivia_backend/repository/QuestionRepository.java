package gabrielmagm.historiatrivia_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gabrielmagm.historiatrivia_backend.models.QuestionModel;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
    List<QuestionModel> findByThemeId(Long themeId);
    List<QuestionModel> findByQuestionTypeId(Long questionTypeId);
    
}
