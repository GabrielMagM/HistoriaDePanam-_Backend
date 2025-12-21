package gabrielmagm.historiatrivia_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gabrielmagm.historiatrivia_backend.models.ThemeModel;
import gabrielmagm.historiatrivia_backend.services.Interfaces.ThemeService;

@RestController
@RequestMapping("/api/themes")
@CrossOrigin(origins = "*")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping("list")
    public ResponseEntity<?> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<?> getThemesBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(themeService.getThemesBySection(sectionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getThemeById(@PathVariable Long id) {
        var theme = themeService.getThemeById(id);
        return theme == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(theme);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTheme(@RequestBody ThemeModel theme) {
        try {
            return ResponseEntity.ok(themeService.createTheme(theme));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTheme(@PathVariable Long id, @RequestBody ThemeModel theme) {
        try {
            var updated = themeService.updateTheme(id, theme);
            return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheme(@PathVariable Long id) {
        return themeService.deleteTheme(id)
                ? ResponseEntity.ok("Tema eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}
