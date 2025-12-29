package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;

import gabrielmagm.historiatrivia_backend.dtos.request.QuestionCreateDTO;
import gabrielmagm.historiatrivia_backend.models.QuestionModel;

public interface QuestionService {
    List<QuestionModel> getAllQuestions();

    QuestionModel getQuestionById(Long id);

    List<QuestionModel> getQuestionsByTheme(Long themeId);

    QuestionModel createQuestion(QuestionCreateDTO dto);

    QuestionModel updateQuestion(Long id, QuestionCreateDTO dto);
    Boolean deleteQuestion(Long id);
}
