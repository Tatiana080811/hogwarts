package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Long id, Faculty faculty);
    boolean deleteFaculty(Long id);
    Faculty readFaculty(Long id);
    void updateFaculty(Long id, String name, String color);
    int countFaculties();
    List<Faculty> findByColor(String color);
}



