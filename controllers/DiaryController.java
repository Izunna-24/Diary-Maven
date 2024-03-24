package myDiary.controllers;

import myDiary.dtos.*;
import myDiary.exceptions.*;
import myDiary.services.DiaryService;
import myDiary.services.DiaryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryServices;

    @GetMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        try {
            diaryServices.loginWith(loginRequest);
            return String.format("%s has logged in successfully );", loginRequest.getUsername());
        }
        catch (DiaryExceptions e) {
            return e.getMessage();
        }

    }

    @PostMapping("/sign_in")
    public String register(@RequestBody RegisterRequest registerRequest){
        try{
            diaryServices.registerWith(registerRequest);
            return String.format("Hello %s, your registration was successful!", registerRequest.getUsername());
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    @PatchMapping("/sign_out")
    public String logout(String username){
        try {
            diaryServices.logout(username);
            return String.format("%s, you are currently logged out!", username);
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }


    @PostMapping("/create_entry")
    public  String createEntry(@RequestBody CreateEntryRequest createEntry){
        try{
            diaryServices.createEntryWith(createEntry);
            return String.format("Hello %s, your entry was created successfully", createEntry.getAuthor());
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    @PatchMapping("/update_entry/{id}")
    public  String updateEntry(@RequestBody UpdateEntryRequest updateEntry){
        try{
            diaryServices.updateEntry(updateEntry);
            return String.format("Hello %s, your entry with id number %d has been created successfully%n", updateEntry.getAuthor(),updateEntry.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete_entry{id}")
    public  String deleteEntry(@RequestBody DeleteEntryRequest deleteEntry){
        try{
            diaryServices.deleteEntry(deleteEntry);
            return String.format("Hello %s, your entry with id number %d has been deleted successfully%n", deleteEntry.getAuthor(), deleteEntry.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }
    @DeleteMapping("/delete_diary{id}")
    public  String deleteDiary(@RequestBody DeleteDiaryRequest deleteDiary){
        try{
            diaryServices.deleteDiary(deleteDiary);
            return String.format("Hello %s, your myDiary.data.models.Diary account with id number %d has been deleted successfully%n", deleteDiary.getAuthor(), deleteDiary.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    @GetMapping("/findAllEntries")
    public  String findAllEntries(String username){
        try{
            return String.format("Entries of %s%n " + "%s", username, diaryServices.findEntriesByAuthor(username));

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }



}