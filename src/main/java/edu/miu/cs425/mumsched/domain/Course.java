package edu.miu.cs425.mumsched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Lob
    private String courseDescription;
    @ManyToOne
    private Course prerequisites;
    @ManyToMany(mappedBy = "preferredCourses")
    private Set<Faculty> faculties;
    private Integer creditHour;
    @Transient
    private String prerequisit;
    @OneToOne(mappedBy = "course")
    private Section sections;

    public static Course getBestCourse(List<Course> courseList) {
        return courseList.size() > 0 ? courseList.remove(0) : null;
    }

    public static List<Course> orderCourses(List<Course> courseList) {
        //first let's put all prerequisites to the front
        List<Course> sortedList = courseList.stream().filter(course -> course.getPrerequisites() == null).collect(Collectors.toList());
        courseList.removeAll(sortedList);
        Collections.shuffle(sortedList);
        Collections.shuffle(courseList);
        sortedList.addAll(courseList);
        sortedList.forEach(course->System.out.println(course.getCourseNumber()));
        return sortedList;
    }
    public void addSection(Section section) {
        if (section != null) {
            this.sections=section;
            section.setCourse(this);
        }
    }

    public boolean removeSection(Section section) {
        if (section != null) {
            section.setCourse(null);sections=null;
            return true ;
        }
        return false;
    }

    public Faculty getBestFaculty(Block block) {
        if (this.getFaculties().isEmpty()) return null;
        Faculty bestFaculty = this.getFaculties().iterator().next();
        int sectionAlreadyTaught = bestFaculty.getSections().size();
        for (Faculty fac : this.getFaculties()) {
            int temp = fac.getSections().size();
            if (sectionAlreadyTaught > temp && fac.isAvailable(block)) {
                bestFaculty = fac;
            }
        }

        return bestFaculty;
    }

}
