package gabrielmagm.historiatrivia_backend.dtos;

import lombok.*;
@Getter
@Setter
public class ThemeDTO {
    private Long id;
    private String title;
    private String description;
    private String imagenUrl;
    private Long sectionId;
    private String sectionTitle;
}
