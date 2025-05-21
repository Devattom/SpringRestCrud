package com.devattom.SpringRestCrud.rest;

import com.devattom.SpringRestCrud.entity.Student;
import com.devattom.SpringRestCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> get()
    {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable int id)
    {
        Student student = studentService.findById(id);

        if(student == null) {
            throw new StudentNotFoundException("Student with id: " + id + " not found");
        }

        return student;
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student newStudent)
    {
        return studentService.save(newStudent);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id)
    {
        studentService.deleteById(id);
    }
}
