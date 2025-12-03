package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Faculty;

import javax.naming.Name;
import javax.persistence.Id;
@Repository
public interface FacultyRepository extends JpaRepository <Faculty, Long> {

}
