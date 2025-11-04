package com.example.calender.repository;

import com.example.calender.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 일정의 댓글 조회 (작성일 기준 내림차순)
    List<Comment> findByCalenderIdOrderByCreatedAtDesc(Long calId);

    // 특정 일정의 댓글 갯수 조회
    int countByCalenderId(Long calId);

}
