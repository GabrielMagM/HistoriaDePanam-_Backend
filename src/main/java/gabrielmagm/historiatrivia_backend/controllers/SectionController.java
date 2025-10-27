package gabrielmagm.historiatrivia_backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gabrielmagm.historiatrivia_backend.models.SectionModel;
import gabrielmagm.historiatrivia_backend.services.Interfaces.SectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/api/sections")
@CrossOrigin(origins = "*")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("list")
    public ResponseEntity <?> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getSectionById(@PathVariable Long id) {
        var section = sectionService.getSectionById(id);
        if (section == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(section);
        
    }

    //Crear una nueva sección
    @PostMapping("/add")
    public ResponseEntity<?> createSection(@RequestBody SectionModel section) {
        try {
            var newSection = sectionService.createSection(section);
            return ResponseEntity.ok(newSection);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Actualizar una sección existente
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSection(@PathVariable Long id, @RequestBody SectionModel section) {
        try {
            SectionModel updatedSection = sectionService.updateSection(id, section);

            if (updatedSection == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Sección no encontrada con ID " + id));
            }

            return ResponseEntity.ok(Map.of(
                    "message", "Sección actualizada correctamente",
                    "data", updatedSection
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error inesperado al actualizar la sección"));
        }
    }
    
    
    //Eliminar una sección
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSection(@PathVariable Long id) {
        try {
            SectionModel deletedSection = sectionService.deleteSection(id);

            if (deletedSection == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Sección no encontrada con ID " + id));
            }

            return ResponseEntity.ok(Map.of(
                    "message", "Sección eliminada correctamente",
                    "data", deletedSection
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error inesperado al eliminar la sección"));
        }
    }
    
}
