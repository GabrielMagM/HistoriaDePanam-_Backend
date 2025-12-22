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
    public ThemeModel updateTheme(Long id, ThemeModel theme) {
        ThemeModel existing = getThemeById(id);
        if (existing == null) return null;

        if (theme.getTitle() != null) existing.setTitle(theme.getTitle());
        if (theme.getDescription() != null) existing.setDescription(theme.getDescription());
        if (theme.getImagenUrl() != null) existing.setImagenUrl(theme.getImagenUrl());

        return themeRepository.save(existing);
    }


    @Override
    public Boolean deleteTheme(Long id) {
        if (!themeRepository.existsById(id)) return false;
        themeRepository.deleteById(id);
        return true;
    }

}
