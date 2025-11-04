package com.example.calender.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCalenderResponse {

    private Long id;
    private String userName;
    private String calTitle;
    private String calContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UpdateCalenderResponse(Long id, String userName, String calTitle,
                                  String calContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
