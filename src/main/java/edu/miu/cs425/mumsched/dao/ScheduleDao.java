package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByStatus(String status);
}
