package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.domain.Course;

import java.util.List;

public interface CourseService {
    Course findCourseByCourseNumber(String courseNumber);
    Course findCourseByCourseTitle(String courseTitle);
    void save(Course course);
    List<Course> getAllCourses();
    Course findCourseById(Long id);
    Course deleteCourse(String courseNumber);
}
