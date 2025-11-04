package com.example.calender.dto;

import com.example.calender.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long id;
    private final String commentName;
    private final String comContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long calId; // 소속된 일정 ID

    public CommentResponse(Long id, String commentName, String comContent,
                           LocalDateTime createdAt, LocalDateTime modifiedAt,
                           Long calId) {
        this.id = id;
        this.commentName = commentName;
        this.comContent = comContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.calId = calId;
    }

    // Entity -> DTO 변환
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getCommentName(),
                comment.getComContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getCalender().getId()
        );
    }

}
