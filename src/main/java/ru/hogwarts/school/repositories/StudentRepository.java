package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
