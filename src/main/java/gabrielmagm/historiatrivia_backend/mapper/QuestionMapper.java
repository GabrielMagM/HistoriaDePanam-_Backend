package gabrielmagm.historiatrivia_backend.mapper;

import java.util.stream.Collectors;

import gabrielmagm.historiatrivia_backend.dtos.response.AnswerResponseDTO;
import gabrielmagm.historiatrivia_backend.dtos.response.QuestionResponseDTO;
import gabrielmagm.historiatrivia_backend.models.QuestionModel;

public class QuestionMapper {
    public static QuestionResponseDTO toResponseDTO(QuestionModel question) {
        QuestionResponseDTO dto = new QuestionResponseDTO();

        dto.setId(question.getId());
        dto.setText(question.getText());

        dto.setThemeId(question.getTheme().getId());
        dto.setThemeTitle(question.getTheme().getTitle());

        dto.setQuestionTypeId(question.getQuestionType().getId());
        dto.setQuestionTypeName(question.getQuestionType().getName());

        dto.setAnswers(
            question.getAnswers()
                .stream()
                .map(answer -> {
                    AnswerResponseDTO a = new AnswerResponseDTO();
                    a.setId(answer.getId());
                    a.setText(answer.getText());
                    return a;
                })
                .collect(Collectors.toList())
        );

        return dto;
    }

}
