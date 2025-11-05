package com.example.calender.controller;

import com.example.calender.dto.*;
import com.example.calender.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    // ResponseEntity.status(HttpStatus.상태).body(응답데이터);
    // ResponseEntity : HTTP 상태코드를 정확히 전달하기 위해 사용
    // 아무런 상태값도 정해주지 않는다면 기본적으로 Spring은 HTTP 상태 코드 '200'으로 전달함.

    // 저장
    @PostMapping("/calenders")
    public ResponseEntity<CreateCalenderResponse> createCalender(
            @RequestBody CreateCalenderRequest createCalenderRequest) {
        CreateCalenderResponse result = calenderService.save(createCalenderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 전체 조회 (작성자명 필터링 가능)
    @GetMapping("/calenders")
    public ResponseEntity<List<GetUserNameCalenderResponse>> getAllCalenders(
            @RequestParam(required = false) String userName) {
        List<GetUserNameCalenderResponse> results = calenderService.findAll(userName);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    // 선택 일정 조회 (단 건 조회) - 댓글 내역 포함
    @GetMapping("/calenders/{id}")
    public ResponseEntity<GetOneCalenderResponse> getOneCalender(@PathVariable Long id) {
        GetOneCalenderResponse result = calenderService.getOneCalender(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 수정
    @PatchMapping("/calenders/{id}")
    public ResponseEntity<UpdateCalenderResponse> updateCalender(
            @PathVariable Long id,
            @RequestBody UpdateCalenderRequest updateCalenderRequest
    ) {
        UpdateCalenderResponse result = calenderService.updateCalender(id, updateCalenderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    // 삭제
    // @RequestParam 사용시, URL에 비밀번호 노출 가능성 증가
    // 보안성을 위해서 @RequestBody 사용
    @DeleteMapping("/calenders/{id}")
    public ResponseEntity<Void> deleteCalender(
            @PathVariable Long id,
            @RequestBody DeleteCalPwdRequest deleteCalPwdRequest
    ) {
        calenderService.deleteCalender(id, deleteCalPwdRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
