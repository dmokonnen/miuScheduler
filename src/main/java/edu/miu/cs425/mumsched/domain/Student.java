package edu.miu.cs425.mumsched.domain;

import lombok.*;

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
//@DiscriminatorValue("student")
public class Student{
    private String track;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer studentId;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String username;
    @ManyToMany(mappedBy = "students", cascade= CascadeType.ALL)
    //enrolled sections
    private Set<Section> sections;
    //student entry
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Entry entry;

}
