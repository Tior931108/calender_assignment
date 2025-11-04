package com.example.calender.controller;

import com.example.calender.dto.CommentResponse;
import com.example.calender.dto.CreateCommentRequest;
import com.example.calender.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
