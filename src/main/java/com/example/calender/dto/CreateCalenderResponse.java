package com.example.calender.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCalenderResponse {

    // 응답하면 바뀌는 속성이 아니기에 final
    private final Long id;
    private final String userName;
    private final String calTitle;
    private final String calContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 비밀번호 제외한 응답
    public CreateCalenderResponse(Long id, String userName, String calTitle, String calContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
