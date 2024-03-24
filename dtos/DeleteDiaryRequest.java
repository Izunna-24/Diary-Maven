package myDiary.dtos;

import lombok.Data;

@Data
public class DeleteDiaryRequest {
    private String username;
    private String password;
}
