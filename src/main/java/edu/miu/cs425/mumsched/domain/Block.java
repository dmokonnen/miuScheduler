package edu.miu.cs425.mumsched.domain;

import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long blockID;
    @NotEmpty
    private String blockName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer numberOfFppCourse;
    private Integer numberOfMppCourse;
//    @JoinColumn(name="entryID",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Entry entry;
    @ManyToMany(mappedBy = "blocks")
    private Set<Faculty> faculties;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private Set<Section> sections;

    public void addSection(Section section) {
        if (section != null) {
            sections.add(section);
            section.setBlock(this);
        }
    }

    public void removeSection(Section section) {
        if (section != null) {
            sections.remove(section);
            section.setBlock(null);
        }
    }

    public void createSections(Course fpp, Course mpp, List<Course> courseList, Entry entry)throws RuntimeException{

        //if the block as previous section , compute available seats
        int seatsAlreadyAvailable = 0;
        for (Section temp : this.getSections()) {
            if (!temp.getName().equalsIgnoreCase("MPP") && !temp.getName().equalsIgnoreCase("FPP"))
                seatsAlreadyAvailable += temp.getAvailableSeats();
        }
        //Now compute how many more seats are needed
        int seatsNeeded = entry.getFPPNum() + entry.getMPPNum();
        //than check how many FPP sections are needed
        if (numberOfFppCourse > 0) {
            int temp = this.numberOfFppCourse;
            while (temp > 0) {
                Section sect = createSection(fpp, temp);
                entry.getSchedule().addSection(sect);
                seatsNeeded -= sect.getCapacity();
                temp--;
            }

        }//end of ffp section creation

        //than check how many MPP sections are needed
        if (numberOfMppCourse > 0) {
            int temp = this.numberOfMppCourse;
            while (temp > 0) {
                Section sect = createSection(mpp, temp);
                entry.getSchedule().addSection(sect);
                temp--;
                seatsNeeded -= sect.getCapacity();
            }
        }//end of mpp section creation

        if (seatsNeeded <= 0)
            return;

        //create extra section
        seatsNeeded -= seatsAlreadyAvailable;
        int temp = 0;
        while (seatsNeeded > 0) {
            Course course = Course.getBestCourse(courseList);
            Section sect = createSection(course, temp);
            entry.getSchedule().addSection(sect);
            seatsNeeded -= sect.getCapacity();
            temp++;
        }//done created extra course
    }

    public Section createSection(Course course, int counter) {
        Section sect = new Section();
        sect.setName(course.getCourseTitle() + "-" + this.getBlockName() + "-" + counter);
//        sect.setCapacity(course.getInitialCapacity());
        sect.setCapacity(30);
        sect.setEnrolled(0);
//        sect.setAvailableSeats(course.getInitialCapacity());
        sect.setAvailableSeats(30);
        course.addSection(sect);
        this.addSection(sect);
        Faculty fact = course.getBestFaculty(this);
        if (fact != null) {
            if(fact.isAvailable(this)) {
                fact.addSection(sect);
                fact.addLockBlock(this);
            }
        }
        return sect;
    }


}
