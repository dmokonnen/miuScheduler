package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Faculty;
import edu.miu.cs425.mumsched.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyDao extends JpaRepository<Faculty,Integer> {
    Faculty getFacultyByEmail(String email);
    Faculty findByUserName(String username);
//    Faculty findByUser(User user);
//    List<Faculty> findAllByUserIs(User user);
    List<Faculty> findAllByPreferredCoursesContains(Course course);
}
