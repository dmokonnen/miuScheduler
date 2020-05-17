package edu.miu.cs425.mumsched.registrationSubSystem.dao;

import edu.miu.cs425.mumsched.domain.Course;
import edu.miu.cs425.mumsched.registrationSubSystem.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionDao extends JpaRepository<Section,Integer> {
    @Query("select s from Section s where s.block.blockID = :blockId and s.id <> :id")
    public List<Section> findByBlockId(@Param("blockId") Integer blockId, @Param("id") Integer id);
    public List<Section> findByCourse(Course course);
}
