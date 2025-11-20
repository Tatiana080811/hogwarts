package ru.hogwarts.school.controller;

import ru.hogwarts.school.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.StudentServiceImpl;

@Controller
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping ("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Student>getStudentInfo(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if (student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping ("{id}")
    public ResponseEntity<Student>editStudent(@RequestBody Student student,
                                              @PathVariable Long id){
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void>deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
