package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Student;
import edu.miu.cs425.mumsched.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);
//    Student findByUser(User user);
//    List<Student> findAllByUserIs(User user);
}
