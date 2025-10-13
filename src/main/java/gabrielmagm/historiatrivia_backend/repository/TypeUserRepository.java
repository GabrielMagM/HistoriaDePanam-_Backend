package gabrielmagm.historiatrivia_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gabrielmagm.historiatrivia_backend.models.TypeUserModel;

public interface TypeUserRepository extends JpaRepository<TypeUserModel, Long> {
    TypeUserModel findByName(String name);
}
