package com.devattom.SpringRestCrud.DAO;

import com.devattom.SpringRestCrud.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student findById(int id);

    Student save(Student student);

    void deleteById(int id);
}
