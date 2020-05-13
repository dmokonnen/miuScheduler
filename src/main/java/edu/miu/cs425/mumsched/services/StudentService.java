package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.domain.Student;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email);
    Student findById(Integer studentId);
    void delete(Integer studentId);
    void save(Student student);
    List<Student> findAll();
}
