package gabrielmagm.historiatrivia_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gabrielmagm.historiatrivia_backend.models.AnswerModel;
import gabrielmagm.historiatrivia_backend.repository.AnswerRepository;
import gabrielmagm.historiatrivia_backend.services.Interfaces.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
    
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<AnswerModel> getAnswersByQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public List<AnswerModel> getCorrectAnswers(Long questionId) {
        return answerRepository.findByQuestionIdAndCorrectTrue(questionId);
    }

}
