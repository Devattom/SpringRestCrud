package com.devattom.SpringRestCrud.rest;

import com.devattom.SpringRestCrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        students.add(new Student("Ali", "Khan"));
        students.add(new Student("Garcia", "Romero"));
        students.add(new Student("Antoine", "Dupont"));
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id)
    {
        if (id > students.size() || id < 0) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }

        return students.get(id);
    }
}
