package edu.miu.cs425.mumsched.dao;

import edu.miu.cs425.mumsched.domain.Entry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Repository
@Transactional
public interface EntryDao extends CrudRepository<Entry,Long> {
    Entry findEntryByEntryID(long entryID);
    Entry findEntryByEntryName(String entryName);
    @Query("select s from Entry s")
    List<Entry> getAllEntry();
}
