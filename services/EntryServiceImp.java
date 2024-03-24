package myDiary.services;



import myDiary.data.models.Entry;
import myDiary.dtos.*;
import myDiary.data.repositories.EntryRepository;
import myDiary.dtos.UpdateEntryRequest;
import myDiary.exceptions.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImp implements EntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public List<Entry> findByAuthor(String author) {

        return entryRepository.findByAuthor(author);
    }

    @Override
    public Entry findByEntryId(String id) {
        Optional<Entry> entry = entryRepository.findById(id);
        if (entry.isEmpty()) throw new EntryNotFoundException("Entry does not exist");
        return entry.get();

    }

    @Override
    public long count() {
        return entryRepository.count();
    }

    @Override
    public void createEntryWith(CreateEntryRequest createEntryRequest) {
        Entry entry = new Entry();
        entry.setTitle(createEntryRequest.getTitle());
        entry.setBody(createEntryRequest.getBody());
        entry.setAuthor(createEntryRequest.getAuthor());
        entryRepository.save(entry);
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest updateEntryRequest) {
        Entry entry = findByEntryId(updateEntryRequest.getId());
        if (!entry.getAuthor().equalsIgnoreCase(updateEntryRequest.getAuthor())) throw new EntryNotFoundException("Entry not found");
        entry.setBody(updateEntryRequest.getBody());
        entry.setTitle(updateEntryRequest.getTitle());
        entryRepository.save(entry);
    }


    @Override
    public void deleteEntry(DeleteEntryRequest deleteRequest) {
        Entry entry = findByEntryId(deleteRequest.getId());
        entryRepository.delete(entry);
    }

    @Override
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }


}