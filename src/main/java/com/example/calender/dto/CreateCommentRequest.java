package com.example.calender.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private String commentName;
    private String comContent;
    private Integer comPwd;
}
