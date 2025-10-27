package gabrielmagm.historiatrivia_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import gabrielmagm.historiatrivia_backend.services.Interfaces.SectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/section")
@CrossOrigin(origins = "*")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("list")
    public ResponseEntity <?> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> getSectionById(@RequestParam Long id) {
        var section = sectionService.getSectionById(id);
        if (section == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(section);
        
    }
    
    
}
