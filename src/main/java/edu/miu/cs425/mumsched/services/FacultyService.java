package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty findById(Integer facultyId);
    void delete(Integer facultyId);
    void save(Faculty faculty);
    List<Faculty> findAll();
    Faculty findByEmail(String email);
    List<Faculty> findAllByPreferredCoursesContains(Course course);
}
