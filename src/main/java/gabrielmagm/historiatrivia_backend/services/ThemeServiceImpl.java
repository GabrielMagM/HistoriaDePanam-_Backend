package gabrielmagm.historiatrivia_backend.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return (List<ThemeModel>) themeRepository.findAll();
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
    public ThemeModel updateTheme(Long id, ThemeModel theme) {
        ThemeModel existingTheme = themeRepository.findById(id).orElse(null);
        if (existingTheme == null) {
            return null;
        }

        if (theme.getTitle() != null && !theme.getTitle().isBlank()) {
            existingTheme.setTitle(theme.getTitle());
        }

        if (theme.getDescription() != null) {
            existingTheme.setDescription(theme.getDescription());
        }

        if (theme.getSection() != null && theme.getSection().getId() != null) {
            SectionModel section = sectionRepository.findById(theme.getSection().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Sección no encontrada"));
            existingTheme.setSection(section);
        }

        return themeRepository.save(existingTheme);
    }

    @Override
    public Boolean deleteTheme(Long id) {
        ThemeModel existingTheme = themeRepository.findById(id).orElse(null);
        if (existingTheme == null) {
            return false;
        }
        themeRepository.deleteById(id);
        return true;
    }

}
