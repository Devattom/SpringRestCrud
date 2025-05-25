package com.devattom.SpringRestCrud.repositories;

import com.devattom.SpringRestCrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
