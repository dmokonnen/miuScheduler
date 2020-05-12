package edu.miu.cs425.mumsched.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 5, message = "*Course Number must have at least 5 characters")
    @Column(unique = true)
    @NotEmpty(message = "*Please provide a course number")
    private String courseNumber;

    @NotEmpty(message = "*Please provide a course Title")
    private String courseTitle;

    @NotEmpty(message = "*Please provide a course Course Description")
    private String courseDescription;
    @ManyToOne
    private Course prerequisites;
    private Integer creditHour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseName) {
        this.courseTitle = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Course getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Course prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Integer getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(Integer creditHour) {
        this.creditHour = creditHour;
    }
}
