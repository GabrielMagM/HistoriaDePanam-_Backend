package gabrielmagm.historiatrivia_backend.mapper;

import gabrielmagm.historiatrivia_backend.dtos.request.ThemeCreateDTO;
import gabrielmagm.historiatrivia_backend.dtos.response.ThemeResponseDTO;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;

public class ThemeMapper {
    public static ThemeResponseDTO toResponseDTO(ThemeModel theme) {
        ThemeResponseDTO dto = new ThemeResponseDTO();
        dto.setId(theme.getId());
        dto.setTitle(theme.getTitle());
        dto.setDescription(theme.getDescription());
        dto.setImagenUrl(theme.getImagenUrl());

        if (theme.getSection() != null) {
            dto.setSectionId(theme.getSection().getId());
            dto.setSectionTitle(theme.getSection().getTitle());
        }

        return dto;
    }

}

    
