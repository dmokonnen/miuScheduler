package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.FacultyDao;
import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.domain.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    FacultyDao facultyDao;
    @Override
    public Faculty findById(Integer facultyId) {
        return facultyDao.getOne(facultyId);
    }

    @Override
    public void delete(Integer facultyId) {
        facultyDao.deleteById(facultyId);
    }

    @Override
    public void save(Faculty faculty) {
        facultyDao.save(faculty);
    }

    @Override
    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }

    @Override
    public Faculty findByEmail(String email) {
        return facultyDao.getFacultyByEmail(email);
    }

    @Override
    public List<Faculty> findAllByPreferredCoursesContains(Course course) {
        return facultyDao.findAllByPreferredCoursesContains(course);
    }

    @Override
    public Faculty findByUserName(String username) {
        return facultyDao.findByUserName(username);
    }
}
