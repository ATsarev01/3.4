package ru.hogwarts.schoolsql.controller;


import ru.hogwarts.schoolsql.entity.Faculty;
import ru.hogwarts.schoolsql.entity.Student;
import ru.hogwarts.schoolsql.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Optional<Student> getStudentInfo(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudent(id);
        return student;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public Optional<Student> editStudent(@RequestBody long id, @RequestBody Student student) {
        Optional<Student> foundStudent = studentService.editStudent(id, student);
        return foundStudent;
    }

    @DeleteMapping("{id}")
    public Optional<Student> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public Collection<Student> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return studentService.findByAge(age);
        }
        return Collections.emptyList();
    }

    @GetMapping(params = {"min, max"})
    public Collection<Student> getAllByAgeBetween(@RequestParam("min") int minAge,
                                                                        @RequestParam ("max") int maxAge) {
        return studentService.getAllByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public Optional<Faculty> getFacultyByStudentId(@PathVariable long id) {
        return studentService.getFacultyByStudentId(id);
    }


}
