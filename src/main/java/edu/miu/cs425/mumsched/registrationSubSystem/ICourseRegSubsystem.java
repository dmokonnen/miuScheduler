package edu.miu.cs425.mumsched.registrationSubSystem;

import edu.miu.cs425.mumsched.domain.*;
import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;

import java.util.List;

public interface ICourseRegSubsystem {
    public Student findStudent(Long studentId);

    public Entry getEntry(Student student);

    public List<Block> getBlock(Entry entry);

    public Section getSection(Long sectionId);

    public List<Section> getAvailableSection(Block block);

    public List<Course> getPrerequisites(Course course);

    public void enrollSection(Student student, Section section);

    public Student saveStudent(Student student);

    public Section saveSection(Section section);


}
