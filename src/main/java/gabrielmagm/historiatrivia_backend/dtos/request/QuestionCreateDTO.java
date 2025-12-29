package gabrielmagm.historiatrivia_backend.dtos.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDTO {
    private String text;
    private Long themeId;
    private Long questionTypeId;
    private List<AnswerCreateDTO> answers;
}
