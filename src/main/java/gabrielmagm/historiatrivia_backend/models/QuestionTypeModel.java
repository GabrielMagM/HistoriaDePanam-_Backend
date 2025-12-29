package gabrielmagm.historiatrivia_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "question_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name; // MULTIPLE_CHOICE, TRUE_FALSE, etc.
    
    @Column(length = 100)
    private String displayName; // "Opción Múltiple", "Verdadero/Falso", etc.
    
    @Column(length = 255)
    private String description; // Descripción opcional del tipo
}
