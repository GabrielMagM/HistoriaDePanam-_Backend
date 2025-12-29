package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;

import gabrielmagm.historiatrivia_backend.models.AnswerModel;

public interface AnswerService {
    
    List<AnswerModel> getAnswersByQuestion(Long questionId);

    List<AnswerModel> getCorrectAnswers(Long questionId);
    
}
