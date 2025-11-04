package com.example.calender.service;

import com.example.calender.dto.CommentResponse;
import com.example.calender.dto.CreateCommentRequest;
import com.example.calender.entity.Calender;
import com.example.calender.entity.Comment;
import com.example.calender.repository.CalenderRepository;
import com.example.calender.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CalenderRepository calenderRepository;
    private final CommentRepository commentRepository;

    // 댓글 생성
    @Transactional
    public CommentResponse createComment(Long calId, CreateCommentRequest createCommentRequest) {
        // 일정 존재 확인
        Calender calender = calenderRepository.findById(calId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        // 댓글 갯수 제한 확인 : 촤대 10개
        int commentCount = commentRepository.countByCalenderId(calId);
        if (commentCount >= 10) {
            throw new IllegalStateException("하나의 일정에는 댓글을 10개까지만 작성할 수 있습니다.");
        }

        // 댓글 생성
        Comment comment = new Comment(
                createCommentRequest.getCommentName(),
                createCommentRequest.getComContent(),
                createCommentRequest.getComPwd(),
                calender
        );

        // 저장
        Comment savedComment = commentRepository.save(comment);

        // 응답
        return CommentResponse.from(savedComment);
    }


    // 특정 일정의 댓글 전체 조회
}
