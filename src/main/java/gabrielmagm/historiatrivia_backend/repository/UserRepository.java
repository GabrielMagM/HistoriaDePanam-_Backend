package gabrielmagm.historiatrivia_backend.repository;

import org.springframework.data.repository.CrudRepository;

import gabrielmagm.historiatrivia_backend.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    boolean existsByEmail(String email);
}
