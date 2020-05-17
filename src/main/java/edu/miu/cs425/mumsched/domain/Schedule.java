package edu.miu.cs425.mumsched.domain;

import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import edu.miu.cs425.mumsched.utilities.ScheduleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
@Data
@AllArgsConstructor
@Entity
public class Schedule implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Date generatedDate;
    private Date approvedDate;
    private ScheduleStatus status;//should be an enum
//    entry of the schedule
    @OneToOne(mappedBy = "schedule",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Entry entry;
    //sections that was generated by this schedule
    @OneToMany(mappedBy = "schedule",cascade = CascadeType.ALL)
    private Set<Section> sections;

    public Schedule() {
        this.sections=new HashSet<>();
    }
    public void setEntry(Entry entry) {
        entry.setSchedule(this);
        this.entry = entry;
    }
    public Schedule generate(List<Course> courseList) throws RuntimeException {
        if(this.getEntry()==null)
            return null;
        if (this.getEntry().getFPPNum()+this.getEntry().getMPPNum() <= 0)
            return null;
        List<Course> courses=Course.orderCourses(courseList);
        Course fpp=findFPPorMPP("CS390",courses);
        courses.remove(fpp);
        Course mpp=findFPPorMPP("CS401",courses);
        courses.remove(mpp);
        for(Block block:this.getEntry().getBlocksList()){
            block.createSections(fpp,mpp,courses,this.getEntry());
        }
        this.setGeneratedDate(new Date(System.currentTimeMillis()));
        this.setStatus(ScheduleStatus.DRAFT);
        return this;
    }

    public void addSection(Section section) {
        if (section != null) {
            sections.add(section);
            section.setSchedule(this);
        }
    }

    public void removeSection(Section section) {
        if (section != null) {
            sections.remove(section);
            section.setSchedule(null);
        }
    }

    private Course  findFPPorMPP(String courseNumber,List<Course> courses)throws RuntimeException{
        for(Course course:courses){
            if(course.getCourseNumber().equalsIgnoreCase(courseNumber)) {
                return course;
            }
        }
        return null;
    }
    public void checkIfEachSectionHasFaculty()throws RuntimeException{
        for(Section sect:this.getSections()){
            if(sect.getFaculty()==null)
                throw new RuntimeException("Some section, in the schedule don't have assigned Faculty");
        }
    }
}
