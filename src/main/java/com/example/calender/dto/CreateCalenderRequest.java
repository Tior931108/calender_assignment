package com.example.calender.dto;

import lombok.Getter;

@Getter
public class CreateCalenderRequest {

    private String userName;
    private String calTitle;
    private String calContent;
    private Integer calPwd;
}
