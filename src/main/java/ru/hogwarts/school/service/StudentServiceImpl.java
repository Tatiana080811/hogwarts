package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long count = 0;

    @Override
    public Student addStudent(Student student) {
        long currentId = ++count;
        student.setId(currentId);
        students.put(currentId, student);
        return student;
    }

    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Long id, Student student) {
        if (!students.containsKey(id)) {
            throw new IllegalArgumentException("Студент с указанным ID не найден");
        }
        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return students.remove(id) != null;
    }
}