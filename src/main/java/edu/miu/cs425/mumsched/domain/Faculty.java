package edu.miu.cs425.mumsched.domain;

import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */

@Getter
@Setter
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_blocks",
            joinColumns = {@JoinColumn(name = "faculty_id")},
            inverseJoinColumns = {@JoinColumn(name = "block_id")})
    private Set<Block> blocks;
    @Transient
    private Set<Block> lockedBlocks;
    @Transient
    private List<String> blockSelections;

    private String specialization;

    public Faculty() {
        this.preferredCourses=new HashSet<>();
    }


    public boolean isAvailable(Block block) {
        List<Block> blocks = this.getBlocks().stream().filter(b1->b1.getBlockName().contains(block.getBlockName())).collect(Collectors.toList());

        if(this.lockedBlocks.contains(block))
            return false;
        return blocks.size() == 0;
    }

    public void addSection(Section section){
        if(section!=null){
            section.setFaculty(this);
            this.sections.add(section);
        }
    }

    public void addLockBlock(Block block){
        if(block!=null){
            this.lockedBlocks.add(block);
        }
    }


}
