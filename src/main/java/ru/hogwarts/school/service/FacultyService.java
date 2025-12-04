package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Long id, Faculty faculty);

    boolean deleteFaculty(Long id);

    List<Faculty> findByColor(String color);
}



