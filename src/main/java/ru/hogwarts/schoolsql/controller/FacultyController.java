package ru.hogwarts.schoolsql.controller;


import org.springframework.data.repository.query.Param;
import ru.hogwarts.schoolsql.entity.Faculty;
import ru.hogwarts.schoolsql.entity.Student;
import ru.hogwarts.schoolsql.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Faculty>> getFacultyInfo(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Optional<Faculty>> editFaculty(@RequestBody long id, @RequestBody Faculty faculty) {
        Optional<Faculty> foundFaculty = facultyService.editFaculty(id, faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "color")
    public ResponseEntity<List<Student>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(params = "nameOrColor")
    public ResponseEntity<List<Student>> findFacultiesByNameOrColor(@RequestParam ("nameOrColor") String nameOrColor)   {
        return facultyService.findFacultiesByNameOrColor(nameOrColor);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentByFacultyId(@PathVariable long id) {
        return facultyService.getStudentByFacultyId(id);
    }


}
