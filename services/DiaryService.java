package myDiary.services;

import myDiary.data.models.Diary;
import myDiary.data.models.Entry;
import myDiary.dtos.*;

import java.util.List;


public interface DiaryService {
    void registerWith(RegisterRequest register);
    Diary findById(String username);
    long count();
    void loginWith(LoginRequest loginRequest);
    void logout(String loginRequest);
    void updateEntry(UpdateEntryRequest updateEntryRequest);
    void deleteEntry(DeleteEntryRequest deleteRequest);
    void createEntryWith(CreateEntryRequest createEntry);
    List<Entry> findEntriesByAuthor(String author);

    void deleteDiary(DeleteDiaryRequest deleteDiaryRequest);
}
