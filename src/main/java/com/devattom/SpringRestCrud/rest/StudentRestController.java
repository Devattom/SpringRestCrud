package com.devattom.SpringRestCrud.rest;

import com.devattom.SpringRestCrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return students.get(id);
    }
}
