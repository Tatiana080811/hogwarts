package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            throw new IllegalArgumentException("Факультет с указанным ID не найден");
        }
        faculties.put(id, faculty);
        return faculty;
    }

    @Override
    public boolean deleteFaculty(Long id) {
        return faculties.remove(id) != null;
    }

    public void createFaculty(String name, String color) {
        long currentId = ++count;
        var faculty = new Faculty(name, currentId, color);
        faculties.put(faculty.getId(), faculty);
    }

    public Faculty readFaculty(Long id) {
        return faculties.get(id);
    }

    public void updateFaculty(Long id, String name, String color) {
        if (!faculties.containsKey(id)) throw new IllegalArgumentException("Факультет с указанным ID не найден");
        var updatedFaculty = new Faculty(name, id, color);
        faculties.put(id, updatedFaculty);
    }

    public int countFaculties() {
        return faculties.size();
    }

    public List<Faculty> findByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
