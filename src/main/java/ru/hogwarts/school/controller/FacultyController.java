package ru.hogwarts.school.controller;

import ru.hogwarts.school.model.Faculty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.service.FacultyService;

@Controller
@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFacultyService(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }


    @GetMapping ("/{id}")
    public ResponseEntity<Faculty>getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<Faculty>editFaculty(@PathVariable Long id,
                                              @RequestBody Faculty faculty){
        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

}
