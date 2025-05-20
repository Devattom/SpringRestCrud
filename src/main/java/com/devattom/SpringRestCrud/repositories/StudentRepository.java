package com.devattom.SpringRestCrud.repositories;

import com.devattom.SpringRestCrud.DAO.StudentDAO;
import com.devattom.SpringRestCrud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> findAll()
    {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s",
                Student.class
        );
        return query.getResultList();
    }
}
