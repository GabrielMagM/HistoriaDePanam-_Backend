package gabrielmagm.historiatrivia_backend.dtos.request;

import lombok.*;
@Getter
@Setter
public class ThemeUpdateDTO {
    private String title;
    private String description;
    private String imagenUrl;
    private Long sectionId;
    
}
