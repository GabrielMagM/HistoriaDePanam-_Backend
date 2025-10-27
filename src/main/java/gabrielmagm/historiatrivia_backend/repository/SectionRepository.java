package gabrielmagm.historiatrivia_backend.repository;

import org.springframework.data.repository.CrudRepository;

import gabrielmagm.historiatrivia_backend.models.SectionModel;

public interface SectionRepository extends CrudRepository<SectionModel, Long> {
    SectionModel findByTitle(String title);
}
