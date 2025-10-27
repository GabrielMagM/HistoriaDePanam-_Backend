package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;

import gabrielmagm.historiatrivia_backend.models.SectionModel;

public interface SectionService {
    List<SectionModel> getAllSections();
    SectionModel getSectionById(Long id);
    SectionModel createSection(SectionModel section);
    SectionModel updateSection(Long id, SectionModel section);
    SectionModel deleteSection(Long id);
}
