package gabrielmagm.historiatrivia_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gabrielmagm.historiatrivia_backend.models.AnswerModel;

public interface AnswerRepository extends JpaRepository<AnswerModel, Long> {
    // Todas las respuestas de una pregunta
    List<AnswerModel> findByQuestionId(Long questionId);

    // Respuestas correctas de una pregunta
    List<AnswerModel> findByQuestionIdAndCorrectTrue(Long questionId);
}
