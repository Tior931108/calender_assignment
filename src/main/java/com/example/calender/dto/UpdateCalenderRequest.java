package com.example.calender.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCalenderRequest {

    private String userName; // 작성자명
    private String calTitle; // 일정 제목
    private Integer calPwd; // 비밀번호
}
