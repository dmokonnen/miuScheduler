package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseByCourseNumber(String courseNumber);
    Course findCourseByCourseTitleContaining(String courseTitle);
}
