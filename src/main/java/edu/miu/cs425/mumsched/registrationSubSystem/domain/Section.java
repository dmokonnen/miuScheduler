package edu.miu.cs425.mumsched.registrationSubSystem.domain;

import edu.miu.cs425.mumsched.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer enrolled;//amount of enrolled student
    private Integer availableSeats;
    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Block block;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Faculty faculty;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Course course;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "section_student",
            joinColumns = {@JoinColumn(name = "section_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName="id")})
    private Set<Student> students;
}
