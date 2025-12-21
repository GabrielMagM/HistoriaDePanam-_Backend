package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;

public interface ThemeService {
    List<ThemeModel> getAllThemes();
    List<ThemeModel> getThemesBySection(Long sectionId);
     ThemeModel getThemeById(Long id);
    ThemeModel createTheme(ThemeModel theme);
    ThemeModel updateTheme(Long id, ThemeModel theme);
    Boolean deleteTheme(Long id);
}