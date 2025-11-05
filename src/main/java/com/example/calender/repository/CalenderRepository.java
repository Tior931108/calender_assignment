package com.example.calender.repository;

import com.example.calender.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    // 작성자명 조회 (수정일 기준 내림차순 조회)
    List<Calender> findByUserNameOrderByModifiedAtDesc(String userName);

    // 전체 조회 (수정일 기준 내림차순)
    List<Calender> findAllByOrderByModifiedAtDesc();

    // 일정ID로 조회 (댓글 Fetch Join) - N + 1 방지
    // 댓글을 한번에 가져오기 위해 fetch 사용
    // :id 는 @Param의 id와 매핑되어 fetch 조인 쿼리문 동작
    @Query("SELECT c FROM Calender c LEFT JOIN FETCH c.comments WHERE c.id = :id")
    Optional<Calender> findByIdWithComments(@Param("id") Long id);
}
