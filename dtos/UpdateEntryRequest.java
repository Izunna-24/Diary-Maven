package myDiary.dtos;

import lombok.Data;

@Data
public class UpdateEntryRequest {
    private String id;
    private String author;
    private String title;
    private String body;
}
