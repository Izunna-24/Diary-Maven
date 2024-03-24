package myDiary.services;


import myDiary.data.models.Entry;
import myDiary.dtos.CreateEntryRequest;
import myDiary.dtos.DeleteEntryRequest;
import myDiary.dtos.UpdateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findByAuthor(String author);
    Entry findByEntryId(String id);
    long count();
   void createEntryWith(CreateEntryRequest createEntryRequest);
   void updateEntryWith(UpdateEntryRequest updateEntry);
   void deleteEntry(DeleteEntryRequest deleteRequest);
   List<Entry> findAllEntries();

}
