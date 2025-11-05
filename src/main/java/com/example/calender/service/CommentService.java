package com.example.calender.service;

import com.example.calender.dto.CommentResponse;
import com.example.calender.dto.CreateCommentRequest;
import com.example.calender.entity.Calender;
import com.example.calender.entity.Comment;
import com.example.calender.repository.CalenderRepository;
import com.example.calender.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByCalenderId(Long calId) {
        // 일정 존재 확인
        if (!calenderRepository.existsById(calId)) {
            throw new IllegalStateException("일정을 찾을 수 없습니다.");
        }

        // 댓글 조회 (작성일 기준 오름차순)
        List<Comment> comments = commentRepository.findByCalenderIdOrderByCreatedAtAsc(calId);

        // response 반환 : 람다식&스트림으로 구현
        return  comments.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

}
