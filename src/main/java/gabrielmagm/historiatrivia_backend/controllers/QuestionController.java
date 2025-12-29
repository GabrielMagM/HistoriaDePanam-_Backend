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

import gabrielmagm.historiatrivia_backend.dtos.request.QuestionCreateDTO;
import gabrielmagm.historiatrivia_backend.mapper.QuestionMapper;
import gabrielmagm.historiatrivia_backend.services.Interfaces.QuestionService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    // Crear pregunta con respuestas
    @PostMapping("/add")
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateDTO dto) {
        try {
            return ResponseEntity.ok(
                QuestionMapper.toResponseDTO(
                    questionService.createQuestion(dto)
                )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener preguntas por tema
    @GetMapping("/theme/{themeId}")
    public ResponseEntity<?> getQuestionsByTheme(@PathVariable Long themeId) {
        var questions = questionService.getQuestionsByTheme(themeId)
                .stream()
                .map(QuestionMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(questions);
    }

    // Obtener una pregunta por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        var question = questionService.getQuestionById(id);
        return question == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(QuestionMapper.toResponseDTO(question));
    }

    // Actualizar pregunta
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateQuestion(
            @PathVariable Long id,
            @RequestBody QuestionCreateDTO dto) {

        var updated = questionService.updateQuestion(id, dto);
        return updated == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(QuestionMapper.toResponseDTO(updated));
    }

    // Eliminar pregunta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id)
                ? ResponseEntity.ok("Pregunta eliminada correctamente")
                : ResponseEntity.notFound().build();
    }

}
