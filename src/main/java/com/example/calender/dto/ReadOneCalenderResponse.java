package com.example.calender.dto;

import com.example.calender.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReadOneCalenderResponse {

    // 응답하면 바뀌는 속성이 아니기에 final
    private final Long id;
    private final String userName;
    private final String calTitle;
    private final String calContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private List<CommentResponse> comments; // 댓글 목록 추가

    // 비밀번호 제외한 응답
    public ReadOneCalenderResponse(Long id, String userName, String calTitle, String calContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // 비빌번호 제외하면서 댓글 포함한 응답
    public ReadOneCalenderResponse(Long id, String userName, String calTitle, String calContent, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        this.id = id;
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }

    // Entity > DTO 변환 (댓글 포함)
    public static ReadOneCalenderResponse fromCalenderAndComments(Calender calender) {
        // 댓글 Response 리스트
        List<CommentResponse> commentsResponseList = calender.getComments().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());

        // 댓글 포함하여 Response
        return new ReadOneCalenderResponse(
                calender.getId(),
                calender.getUserName(),
                calender.getCalTitle(),
                calender.getCalContent(),
                calender.getCreatedAt(),
                calender.getModifiedAt(),
                commentsResponseList
        );
    }
}
