package com.devattom.SpringRestCrud.DAO;

import com.devattom.SpringRestCrud.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();
}
