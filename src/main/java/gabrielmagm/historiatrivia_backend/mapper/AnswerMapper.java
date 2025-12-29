package gabrielmagm.historiatrivia_backend.mapper;

import gabrielmagm.historiatrivia_backend.dtos.response.AnswerResponseDTO;
import gabrielmagm.historiatrivia_backend.models.AnswerModel;
import lombok.Builder;

@Builder
public class AnswerMapper {
    public static AnswerResponseDTO toDTO(AnswerModel answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .text(answer.getText())
                .build();
    }
}
