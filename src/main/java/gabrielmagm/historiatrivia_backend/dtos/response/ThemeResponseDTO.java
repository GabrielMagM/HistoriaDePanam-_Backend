package gabrielmagm.historiatrivia_backend.dtos.response;

import lombok.*;
@Getter
@Setter
public class ThemeResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String imagenUrl;
    private Long sectionId;
    private String sectionTitle;
}
