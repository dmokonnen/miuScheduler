package edu.miu.cs425.mumsched.registrationSubSystem.domain;

import edu.miu.cs425.mumsched.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistrationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Student student;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    @OneToMany
    private Set<Section> sections;
}
