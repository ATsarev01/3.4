package ru.hogwarts.schoolsql.repository;

import ru.hogwarts.schoolsql.entity.Faculty;
import ru.hogwarts.schoolsql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findAllByColor(String color);
    List<Faculty> findAllByNameContainsIgnoreCaseOrColorContainsIgnoreCase(String name, String color);

}
