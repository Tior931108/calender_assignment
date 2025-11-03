package com.example.calender.repository;

import com.example.calender.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    // 작성자명 조회 (수정일 기준 내림차순 조회)
    List<Calender> findByUserNameOrderByModifiedAtDesc(String userName);

    // 전체 조회 (수정일 기준 내림차순)
    List<Calender> findAllByOrderByModifiedAtDesc();
}
