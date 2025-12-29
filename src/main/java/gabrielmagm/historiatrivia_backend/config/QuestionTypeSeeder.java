package gabrielmagm.historiatrivia_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import gabrielmagm.historiatrivia_backend.models.QuestionTypeModel;
import gabrielmagm.historiatrivia_backend.repository.QuestionTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor 
@Slf4j
public class QuestionTypeSeeder implements CommandLineRunner {

    private final QuestionTypeRepository questionTypeRepository;

    @Override
    public void run(String... args) {
        log.info("Iniciando seed de tipos de preguntas...");
        
        createIfNotExists("MULTIPLE_CHOICE", "Opción Múltiple");
        createIfNotExists("TRUE_FALSE", "Verdadero/Falso");
    }

    private void createIfNotExists(String name, String displayName) {
        if (!questionTypeRepository.existsByName(name)) {
            QuestionTypeModel type = QuestionTypeModel.builder()
                    .name(name)
                    .displayName(displayName)
                    .build();
            questionTypeRepository.save(type);
            log.info(" Tipo de pregunta creado: {}", name, displayName);
        } else {
            log.info(" Tipo de pregunta ya existe: {}", name, displayName);
        }
    }
}