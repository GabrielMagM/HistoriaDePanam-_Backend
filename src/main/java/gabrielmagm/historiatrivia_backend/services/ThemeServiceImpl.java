package gabrielmagm.historiatrivia_backend.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gabrielmagm.historiatrivia_backend.dtos.request.ThemeUpdateDTO;
import gabrielmagm.historiatrivia_backend.models.SectionModel;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;
import gabrielmagm.historiatrivia_backend.repository.SectionRepository;
import gabrielmagm.historiatrivia_backend.repository.ThemeRepository;
import gabrielmagm.historiatrivia_backend.services.Interfaces.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {
    
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<ThemeModel> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public List<ThemeModel> getThemesBySection(Long sectionId) {
        return themeRepository.findBySectionId(sectionId);
    }

    @Override
    public ThemeModel getThemeById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    @Override
    public ThemeModel createTheme(ThemeModel theme) {
        if (themeRepository.findByTitle(theme.getTitle()) != null) {
            throw new IllegalArgumentException("El tema con el nombre '" + theme.getTitle() + "' ya existe");
        }
         SectionModel section = sectionRepository.findById(theme.getSection().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sección no encontrada"));

        if (themeRepository.existsByTitleAndSectionId(theme.getTitle(), section.getId())) {
            throw new IllegalArgumentException("El tema ya existe en esta sección");
        }

        return themeRepository.save(theme);
    }
    
    @Override
    public ThemeModel updateTheme(Long id, ThemeUpdateDTO dto) {

        ThemeModel existing = themeRepository.findById(id).orElse(null);
        if (existing == null) return null;

        if (dto.getTitle() != null) {
            existing.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription());
        }

        if (dto.getImagenUrl() != null) {
            existing.setImagenUrl(dto.getImagenUrl());
        }

        if (dto.getSectionId() != null) {
            SectionModel section = sectionRepository
                    .findById(dto.getSectionId())
                    .orElseThrow(() -> new IllegalArgumentException("Sección no válida"));
            existing.setSection(section);
        }

        return themeRepository.save(existing);
    }


    @Override
    public Boolean deleteTheme(Long id) {
        if (!themeRepository.existsById(id)) return false;
        themeRepository.deleteById(id);
        return true;
    }

}
