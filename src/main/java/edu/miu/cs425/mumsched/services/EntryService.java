package edu.miu.cs425.mumsched.services;


import edu.miu.cs425.mumsched.domain.Entry;

import java.util.List;

public interface EntryService {
    void save(Entry entry);
    Entry getEntryByEntryID(long entryId);
    Entry getEntryByEntryName(String entryName);
    List<Entry> getAllEntry();
    void delete(Entry entry);
}
