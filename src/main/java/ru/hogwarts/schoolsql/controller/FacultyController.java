package ru.hogwarts.schoolsql.controller;


import ru.hogwarts.schoolsql.entity.Faculty;
import ru.hogwarts.schoolsql.entity.Student;
import ru.hogwarts.schoolsql.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public Optional<Faculty> getFacultyInfo(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.findFaculty(id);
        return faculty;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public Optional<Faculty> editFaculty(@RequestBody long id, @RequestBody Faculty faculty) {
        Optional<Faculty> foundFaculty = facultyService.editFaculty(id, faculty);
        return foundFaculty;
    }

    @DeleteMapping("{id}")
    public Optional<Faculty> deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping(params = "color")
    public Collection<Faculty> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return facultyService.findByColor(color);
        }
        return Collections.emptyList();
    }

    @GetMapping(params = "nameOrColor")
    public Collection<Faculty> findFacultiesByNameOrColor(@RequestParam("nameOrColor") String nameOrColor)   {
        return facultyService.findFacultiesByNameOrColor(nameOrColor);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentByFacultyId(@PathVariable long id) {
        return facultyService.getStudentByFacultyId(id);
    }


}
