package com.example.calender.controller;

import com.example.calender.dto.CommentResponse;
import com.example.calender.dto.CreateCommentRequest;
import com.example.calender.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/calenders/{calId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long calId,
            @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        CommentResponse result = commentService.createComment(calId, createCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 특정 일정의 댓글 전체 조회
    @GetMapping("/calenders/{calId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByCalenderId(
            @PathVariable Long calId) {
        List<CommentResponse> results = commentService.getCommentsByCalenderId(calId);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
