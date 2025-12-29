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

import gabrielmagm.historiatrivia_backend.dtos.request.ThemeUpdateDTO;
import gabrielmagm.historiatrivia_backend.mapper.ThemeMapper;
import gabrielmagm.historiatrivia_backend.models.ThemeModel;
import gabrielmagm.historiatrivia_backend.services.Interfaces.ThemeService;

@RestController
@RequestMapping("/api/themes")
@CrossOrigin(origins = "*")
public class ThemeController {
    @Autowired
    private ThemeService themeService;


    //Lista de todos los temas
    @GetMapping("list")
    public ResponseEntity<?> getAllThemes() {
        var themes = themeService.getAllThemes()
                .stream()
                .map(ThemeMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(themes);
    }

    //Lista de temas por sección
    @GetMapping("/{id}")
    public ResponseEntity<?> getThemeById(@PathVariable Long id) {
        ThemeModel theme = themeService.getThemeById(id);
        return theme == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(ThemeMapper.toResponseDTO(theme));
    }

    //Crear un nuevo tema
    @PostMapping("/add")
    public ResponseEntity<?> createTheme(@RequestBody ThemeModel theme) {
        try {
            return ResponseEntity.ok(themeService.createTheme(theme));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //Actualizar un tema existente
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTheme(@PathVariable Long id,@RequestBody ThemeUpdateDTO dto) {
        ThemeModel updated = themeService.updateTheme(id, dto);
        return ResponseEntity.ok(ThemeMapper.toResponseDTO(updated));
    }


    //Eliminar un tema por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheme(@PathVariable Long id) {
        return themeService.deleteTheme(id)
                ? ResponseEntity.ok("Tema eliminado correctamente")
                : ResponseEntity.notFound().build();
    }
}
