package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

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
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student editStudent(Long id, Student student) {
        return studentRepository.save(student);
    }
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(Long id) {
            if (!studentRepository.existsById(id)) {
                return false;
            }
            studentRepository.deleteById(id);
            return true;
        }
}
