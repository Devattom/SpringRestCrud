package com.devattom.SpringRestCrud.rest;

import com.devattom.SpringRestCrud.entity.Student;
import com.devattom.SpringRestCrud.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final StudentService studentService;

    private final ObjectMapper objectMapper;

    @Autowired
    public StudentRestController(StudentService studentService, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
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
        newStudent.setId(0);
        return studentService.save(newStudent);
    }

    @PutMapping("/students")
    public Student update(@RequestBody Student student)
    {
        return studentService.save(student);
    }

    @PatchMapping("/students/{id}")
    public Student partialUpdate(@RequestBody Map<String, Object> payload, @PathVariable int id)
    {
        if (payload.containsKey("id")) {
            throw new RuntimeException("Should not have id in the payload");
        }

        Student currentStudent = studentService.findById(id);

        if (currentStudent == null) {
            throw new StudentNotFoundException("Student with id: " + id + " not found");
        }

        Student patchedStudent = apply(payload, currentStudent);

        return studentService.save(patchedStudent);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id)
    {
        studentService.deleteById(id);
    }

    private Student apply(Map<String, Object> payload, Student currentStudent)
    {
        ObjectNode studentNode = objectMapper.convertValue(currentStudent, ObjectNode.class);

        ObjectNode payloadNode = objectMapper.convertValue(payload, ObjectNode.class);

        studentNode.setAll(payloadNode);

        return objectMapper.convertValue(studentNode, Student.class);
    }
}
