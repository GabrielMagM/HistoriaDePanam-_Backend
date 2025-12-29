package gabrielmagm.historiatrivia_backend.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDTO {
     private Long id;
    private String text;

    private Long themeId;
    private String themeTitle;

    private Long questionTypeId;
    private String questionTypeName;

    private List<AnswerResponseDTO> answers;
}
