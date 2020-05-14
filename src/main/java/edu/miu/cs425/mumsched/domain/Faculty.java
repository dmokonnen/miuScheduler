package edu.miu.cs425.mumsched.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
//@DiscriminatorValue("faculty")
public class Faculty{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int facultyID;
    private String firstName;
    private String lastName;
    private String userName;
//    private Gender gender;
    @Column(unique = true)
    private String email;
//    @OneToOne
//    private User user;
    @OneToMany(mappedBy = "faculty")
    private Set<Section> sections;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_courses",
            joinColumns = {@JoinColumn(name = "faculty_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> preferredCourses;

    private String specialization;

    public Faculty() {
        this.preferredCourses=new HashSet<>();
    }
}
