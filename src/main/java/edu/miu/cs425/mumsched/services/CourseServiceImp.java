package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.CourseRepository;
import edu.miu.cs425.mumsched.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Course findCourseByCourseNumber(String courseNumber) {
        return courseRepository.findCourseByCourseNumber(courseNumber);
    }

    @Override
    public Course findCourseByCourseTitle(String courseTitle) {
        return courseRepository.findCourseByCourseTitleContaining(courseTitle);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course deleteCourse(String courseNumber) {
        Course course=courseRepository.findCourseByCourseNumber(courseNumber);
        courseRepository.delete(course);
        return course;
    }
}
