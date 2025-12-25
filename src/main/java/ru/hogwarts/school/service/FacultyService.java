package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(Long id) throws EntityNotFoundException;

    Faculty editFaculty(Long id, Faculty faculty);

    boolean deleteFaculty(Long id);

    List<Faculty> findByColor(String color);


    List<Faculty> searchFaculties(String query);
}