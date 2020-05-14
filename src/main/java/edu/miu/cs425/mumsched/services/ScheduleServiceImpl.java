package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.ScheduleDao;
import edu.miu.cs425.mumsched.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;
    @Override
    public Schedule findById(Integer scheduleId) {
        return scheduleDao.getOne(scheduleId);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleDao.findAll();
    }

    @Override
    public void delete(Integer scheduleId) {
        scheduleDao.deleteById(scheduleId);
    }

    @Override
    public Schedule saveOrUpdate(Schedule schedule) {
        return scheduleDao.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleDao.delete(schedule);
    }

}
