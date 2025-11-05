package com.example.calender.controller;

import com.example.calender.dto.CommentResponse;
import com.example.calender.dto.CreateCommentRequest;
import com.example.calender.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/calenders/{calId}/comments")
    public CommentResponse createComment(
            @PathVariable Long calId,
            @RequestBody CreateCommentRequest createCommentRequest) {
        return commentService.createComment(calId, createCommentRequest);
    }

    // 특정 일정의 댓글 전체 조회
    @GetMapping("/calenders/{calId}/comments")
    public List<CommentResponse> getCommentsByCalenderId(
            @PathVariable Long calId) {
        return commentService.getCommentsByCalenderId(calId);
    }
}
