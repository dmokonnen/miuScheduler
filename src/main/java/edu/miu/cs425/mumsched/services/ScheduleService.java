package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule findById(Integer scheduleId);
    List<Schedule> findAll();
    void delete(Integer scheduleId);
    Schedule saveOrUpdate(Schedule schedule);
    void delete(Schedule schedule);
}
