package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

public interface StudentService {
    Student addStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Long id, Student student);

    void deleteStudent(Long id);
}
