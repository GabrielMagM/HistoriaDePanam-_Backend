package gabrielmagm.historiatrivia_backend.dtos.request;

import lombok.*;
@Getter
@Setter
public class ThemeCreateDTO {
    private Long id;
    private String title;
    private String description;
    private String imagenUrl;
    private Long sectionId;
    private String sectionTitle;
}
