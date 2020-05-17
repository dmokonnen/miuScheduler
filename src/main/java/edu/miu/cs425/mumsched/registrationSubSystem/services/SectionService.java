package edu.miu.cs425.mumsched.registrationSubSystem.services;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */

public interface SectionService {
    public Section findById(Integer sectionId);
    public void delete(Integer sectionId);
    public void save(Section section);
    public List<Section> findAll();

    public List<Section> findByBlockId(Integer blockId, Integer id);
    public List<Section> findByCourse(Course course);
}
