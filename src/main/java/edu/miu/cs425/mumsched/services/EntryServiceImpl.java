package edu.miu.cs425.mumsched.services;


import edu.miu.cs425.mumsched.dao.EntryDao;
import edu.miu.cs425.mumsched.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Demisew Mokonnen
 * @2020
 */
@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    EntryDao entryDao;
    @Override
    public void save(Entry entry) {
        entryDao.save(entry);
        return;
    }

    @Override
    public Entry getEntryByEntryID(long entryId) {

        return entryDao.findEntryByEntryID(entryId);
    }

    @Override
    public Entry getEntryByEntryName(String entryName) {
        return entryDao.findEntryByEntryName(entryName);
    }

    @Override
    public List<Entry> getAllEntry() {
        return entryDao.getAllEntry();
    }
}
