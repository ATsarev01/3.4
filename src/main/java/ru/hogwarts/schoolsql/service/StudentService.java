package ru.hogwarts.schoolsql.service;

import ru.hogwarts.schoolsql.entity.Student;
import ru.hogwarts.schoolsql.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> editStudent(long id, Student newStudent) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(newStudent.getName());
                    oldStudent.setAge(newStudent.getAge());
                    return studentRepository.save(oldStudent);
                });
    }

    public Collection<Student> getALl() {
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Optional<Student> deleteStudent(long id) {
     return studentRepository.findById(id)
             .map(student -> {
                 studentRepository.delete(student);
                 return student;
             });
    }


    public Collection<Student> findByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public ResponseEntity<Collection<Student>> getAllByAgeBetween(int minAge, int maxAge) {
        return (ResponseEntity<Collection<Student>>) studentRepository.findAllByAgeBetween(minAge, maxAge);
    }
}
