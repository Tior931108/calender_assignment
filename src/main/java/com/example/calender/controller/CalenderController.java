package com.example.calender.controller;

import com.example.calender.dto.CreateCalenderRequest;
import com.example.calender.dto.CreateCalenderResponse;
import com.example.calender.dto.GetUserNameCalenderRequest;
import com.example.calender.dto.GetUserNameCalenderResponse;
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
}
