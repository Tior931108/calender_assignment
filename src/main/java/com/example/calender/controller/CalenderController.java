package com.example.calender.controller;

import com.example.calender.dto.*;
import com.example.calender.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    // 저장
    @PostMapping("/calenders")
    public CreateCalenderResponse createCalender(
            @RequestBody CreateCalenderRequest createCalenderRequest) {
        return calenderService.save(createCalenderRequest);
    }

    // 전체 조회 (작성자명 필터링 가능)
    @GetMapping("/calenders")
    public List<GetUserNameCalenderResponse> getAllCalenders(
            @RequestParam(required = false) String userName) {
        return calenderService.findAll(userName);
    }

    // 선택 일정 조회 (단 건 조회)
    @GetMapping("/calenders/{id}")
    public GetOneCalenderResponse getOneCalender(@PathVariable Long id) {
        return calenderService.getOneCalender(id);
    }

    // 수정
    @PutMapping("/calenders/{id}")
    public UpdateCalenderResponse updateCalender(
            @PathVariable Long id,
            @RequestBody UpdateCalenderRequest updateCalenderRequest
    ) {
        return calenderService.updateCalender(id, updateCalenderRequest);
    }


    // 삭제
    // @RequestParam 사용시, URL에 비밀번호 노출 가능성 증가
    // 보안성을 위해서 @RequestBody 사용
    @DeleteMapping("/calenders/{id}")
    public void deleteCalender(
            @PathVariable Long id,
            @RequestBody DeleteCalPwdRequest deleteCalPwdRequest
    ) {
        calenderService.deleteCalender(id, deleteCalPwdRequest);
    }
}
