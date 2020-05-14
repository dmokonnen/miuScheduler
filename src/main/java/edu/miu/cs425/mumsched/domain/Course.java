package edu.miu.cs425.mumsched.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

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

}
