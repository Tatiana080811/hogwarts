package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableJpaRepositories
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Faculty editFaculty(Long id, Faculty updatedFaculty) {
        if (!facultyRepository.existsById(id)) {
            throw new IllegalArgumentException("Факультет с таким ID не найден.");
        }
        updatedFaculty.setId(id);
        return facultyRepository.save(updatedFaculty);
    }

    @Transactional
    @Override
    public boolean deleteFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            return false;
        }
        facultyRepository.deleteById(id);
        return true;
    }
    public List<Faculty> findByColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
}
    }