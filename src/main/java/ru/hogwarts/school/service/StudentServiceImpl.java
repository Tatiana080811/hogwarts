package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Long id, Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {

    }
}