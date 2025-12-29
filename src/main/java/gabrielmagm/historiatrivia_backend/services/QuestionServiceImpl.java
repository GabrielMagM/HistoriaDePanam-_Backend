package gabrielmagm.historiatrivia_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gabrielmagm.historiatrivia_backend.dtos.request.QuestionCreateDTO;
import gabrielmagm.historiatrivia_backend.dtos.response.QuestionResponseDTO;
import gabrielmagm.historiatrivia_backend.mapper.QuestionMapper;
import gabrielmagm.historiatrivia_backend.models.AnswerModel;
import gabrielmagm.historiatrivia_backend.models.QuestionModel;
import gabrielmagm.historiatrivia_backend.models.QuestionTypeModel;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;
import gabrielmagm.historiatrivia_backend.repository.AnswerRepository;
import gabrielmagm.historiatrivia_backend.repository.QuestionRepository;
import gabrielmagm.historiatrivia_backend.repository.QuestionTypeRepository;
import gabrielmagm.historiatrivia_backend.repository.ThemeRepository;
import gabrielmagm.historiatrivia_backend.services.Interfaces.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<QuestionModel> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public QuestionModel getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<QuestionModel> getQuestionsByTheme(Long themeId) {
        return questionRepository.findByThemeId(themeId);
    }

    @Override
    public QuestionModel createQuestion(QuestionCreateDTO dto) {

        ThemeModel theme = themeRepository.findById(dto.getThemeId())
                .orElseThrow(() -> new IllegalArgumentException("Tema no válido"));

        QuestionTypeModel questionType = questionTypeRepository.findById(dto.getQuestionTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pregunta no válido"));

        QuestionModel question = QuestionModel.builder()
                .text(dto.getText())
                .theme(theme)
                .questionType(questionType)
                .build();

        QuestionModel savedQuestion = questionRepository.save(question);

        // Guardar respuestas
        for (var answerDTO : dto.getAnswers()) {
            AnswerModel answer = AnswerModel.builder()
                    .text(answerDTO.getText())
                    .correct(answerDTO.isCorrect())
                    .question(savedQuestion)
                    .build();
            answerRepository.save(answer);
        }

        return savedQuestion;
    }

    @Override
public QuestionModel updateQuestion(Long id, QuestionCreateDTO dto) {

    QuestionModel question = questionRepository.findById(id).orElse(null);
    if (question == null) return null;

    if (dto.getText() != null) {
        question.setText(dto.getText());
    }

    if (dto.getQuestionTypeId() != null) {
        var type = questionTypeRepository.findById(dto.getQuestionTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo inválido"));
        question.setQuestionType(type);
    }

    if (dto.getAnswers() != null) {
        question.getAnswers().clear();

        dto.getAnswers().forEach(a -> {
            question.getAnswers().add(
                AnswerModel.builder()
                    .text(a.getText())
                    .correct(a.isCorrect())
                    .question(question)
                    .build()
            );
        });
    }

    return questionRepository.save(question);
}

    @Override
    public Boolean deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) return false;
        questionRepository.deleteById(id);
        return true;
    }


}
