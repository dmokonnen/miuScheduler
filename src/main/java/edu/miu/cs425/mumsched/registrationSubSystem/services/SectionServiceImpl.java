package edu.miu.cs425.mumsched.registrationSubSystem.services;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.registrationSubSystem.dao.SectionDao;
import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionDao sectionDao;
    @Override
    public Section findById(Integer sectionId) {
        return sectionDao.getOne(sectionId);
    }

    @Override
    public void delete(Integer sectionId) {
        sectionDao.deleteById(sectionId);
    }

    @Override
    public void save(Section section) {
        sectionDao.save(section);
    }

    @Override
    public List<Section> findAll() {
        return sectionDao.findAll();
    }

    @Override
    public List<Section> findByBlockId(Integer blockId, Integer id) {
        return sectionDao.findByBlockId(blockId,id);
    }

    @Override
    public List<Section> findByCourse(Course course) {
        return sectionDao.findByCourse(course);
    }
}
