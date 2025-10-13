package gabrielmagm.historiatrivia_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "type_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeUserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo 'name' no puede estar vacío")
    @Column(nullable = false, unique = true, length = 50)
    private String type_name;

    @Column(length = 255)
    private String description;
}
