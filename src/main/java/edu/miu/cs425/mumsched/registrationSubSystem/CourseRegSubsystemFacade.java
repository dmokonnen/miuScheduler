package edu.miu.cs425.mumsched.registrationSubSystem;

import edu.miu.cs425.mumsched.domain.*;

import java.util.List;

public class CourseRegSubsystemFacade implements ICourseRegSubsystem{

    @Override
    public Student findStudent(Long studentId) {
        return null;
    }

    @Override
    public Entry getEntry(Student student) {
        return null;
    }

    @Override
    public List<Block> getBlock(Entry entry) {
        return null;
    }

    @Override
    public Section getSection(Long sectionId) {
        return null;
    }

    @Override
    public List<Section> getAvailableSection(Block block) {
        return null;
    }

    @Override
    public List<Course> getPrerequisites(Course course) {
        return null;
    }

    @Override
    public void enrollSection(Student student, Section section) {

    }

    @Override
    public Student saveStudent(Student student) {
        return null;
    }

    @Override
    public Section saveSection(Section section) {
        return null;
    }
}
