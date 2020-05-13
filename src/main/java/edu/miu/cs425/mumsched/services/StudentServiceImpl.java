package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.StudentDao;
import edu.miu.cs425.mumsched.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student findByEmail(String email) {
        return studentDao.findByEmail(email);
    }

    @Override
    public Student findById(Integer studentId) {
        return studentDao.getOne(studentId);
    }

    @Override
    public void delete(Integer studentId) {
        studentDao.deleteById(studentId);
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
