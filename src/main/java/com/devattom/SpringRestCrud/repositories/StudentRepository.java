package com.devattom.SpringRestCrud.repositories;

import com.devattom.SpringRestCrud.DAO.StudentDAO;
import com.devattom.SpringRestCrud.entity.Student;
import com.devattom.SpringRestCrud.rest.StudentNotFoundException;
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

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public void deleteById(int id) {
        Student student = findById(id);

        if (student == null) {
            throw new StudentNotFoundException("enable to find the student");
        }

        entityManager.remove(student);
    }
}
