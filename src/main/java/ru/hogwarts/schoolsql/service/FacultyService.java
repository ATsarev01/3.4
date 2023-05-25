package ru.hogwarts.schoolsql.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.schoolsql.entity.Faculty;
import ru.hogwarts.schoolsql.entity.Student;
import ru.hogwarts.schoolsql.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolsql.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty   );
    }

    public Optional<Faculty> findFaculty(long id) {
        return facultyRepository.findById(id);
    }

    public Optional<Faculty> editFaculty(long id, Faculty newFaculty) {
        return facultyRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(newFaculty.getName());
                    oldStudent.setColor(newFaculty.getColor());
                    return facultyRepository.save(oldStudent);
                });
    }

    public Optional<Faculty> deleteFaculty(long id) {
        return facultyRepository.findById(id)
                .map(faculty -> {
                    facultyRepository.delete(faculty);
                    return faculty;
                });
    }

    // Service
    public List<Student> findByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public ResponseEntity<List<Student>> findFacultiesByNameOrColor(String nameOrColor) {
        return (ResponseEntity<List<Student>>) facultyRepository.findAllByNameContainsIgnoreCaseOOrColorContainsIgnoreCase
                (nameOrColor, nameOrColor) ;
    }

    public List<Student> getStudentByFacultyId(long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }
}
