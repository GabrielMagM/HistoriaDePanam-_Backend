package gabrielmagm.historiatrivia_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gabrielmagm.historiatrivia_backend.models.SectionModel;
import gabrielmagm.historiatrivia_backend.repository.SectionRepository;
import gabrielmagm.historiatrivia_backend.services.Interfaces.SectionService;

@Service
public class SectionServiceImpl implements SectionService {
    
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<SectionModel> getAllSections() {
        return (List<SectionModel>) sectionRepository.findAll();
    }

    @Override
    public SectionModel getSectionById(Long id) {
        return sectionRepository.findById(id).orElse(null);
    }

    @Override
    public SectionModel createSection(SectionModel section) {
        if (sectionRepository.findByTitle(section.getTitle()) != null) {
            throw new IllegalArgumentException("La sección con el nombre '" + section.getTitle() + "' ya existe");
        }
        return sectionRepository.save(section);
    }

    @Override
    public SectionModel updateSection(Long id, SectionModel section) {
        SectionModel existingSection = sectionRepository.findById(id).orElse(null);
        if (existingSection == null) {
            return null;
        }
        existingSection.setTitle(section.getTitle());
        existingSection.setDescription(section.getDescription());
        return sectionRepository.save(existingSection);
    }

    @Override
    public SectionModel deleteSection(Long id) {
        SectionModel existingSection = sectionRepository.findById(id).orElse(null);
        if (existingSection == null) {
            return null;
        }
        sectionRepository.delete(existingSection);
        return existingSection;
    }
}
