package myDiary.services;

import myDiary.data.models.Diary;
import myDiary.data.models.Entry;
import myDiary.data.repositories.DiaryRepository;
import myDiary.dtos.*;
import myDiary.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DiaryServiceImp implements DiaryService{
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EntryService entryService;


    @Override
    public void registerWith(RegisterRequest request) {
        validateRegistration(request);
         Diary diary = new Diary();
         diary.setUsername(request.getUsername());
         diary.setPassword(request.getPassword());
         diaryRepository.save(diary);
    }

    private void validateRegistration(RegisterRequest request) {
        if (request.getUsername() == null || request.getPassword() == null ){
            throw new InvalidInputException("Wrong login Details");}
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new InvalidInputException("Wrong login details ");
        }
        if(diaryRepository.existsById(request.getUsername())){
            throw new UsernameAlreadyExitsException("Username not available");
        }
    }

    @Override
    public Diary findById(String username) {
        Optional<Diary> diary = diaryRepository.findById(username);
        if (diary.isEmpty()) {throw new DiaryDoesNotExistException("Diary not found");
            }
         return diary.get();

    }

    @Override
    public long count() {
        return diaryRepository.count();
    }
    @Override
    public void loginWith(LoginRequest loginRequest) {
        Diary diary = findById(loginRequest.getUsername());
        validateLogin(loginRequest, diary);
        diary.setLocked(false);
        diaryRepository.save(diary);
    }
    private static void validateLogin(LoginRequest loginRequest, Diary diary) {
        if (!diary.getPassword().equals(loginRequest.getPassword())){
            throw new WrongPasswordException("Check your details and try again");
        }
        if (!diary.getUsername().equalsIgnoreCase(loginRequest.getUsername())){
            throw new WrongUserNameException("Check your details and try again");
        }

    }
    @Override
    public void logout(String username) {
        Diary diary = findById(username);
        diary.setLocked(true);
        diaryRepository.save(diary);
    }

    @Override
    public void updateEntry(UpdateEntryRequest updateEntryRequest) {
        findById(updateEntryRequest.getAuthor());
        entryService.updateEntryWith(updateEntryRequest);
    }

    @Override
    public void deleteEntry(DeleteEntryRequest deleteRequest) {
        findById(deleteRequest.getAuthor());
        entryService.deleteEntry(deleteRequest);
    }

    @Override
    public void createEntryWith(CreateEntryRequest createEntry) {
        findById(createEntry.getAuthor());
        entryService.createEntryWith(createEntry);
    }

    @Override
    public List<Entry> findEntriesByAuthor(String username) {
        return entryService.findByAuthor(username);
    }

    @Override
    public void deleteDiary(DeleteDiaryRequest deleteDiaryRequest) {
        Diary diary = findById(deleteDiaryRequest.getUsername());
        if (!diary.getPassword().equals(deleteDiaryRequest.getPassword())) throw new WrongPasswordException("Invalid details");
        diaryRepository.delete(diary);
    }

}
