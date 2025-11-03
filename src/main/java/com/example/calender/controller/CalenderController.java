package com.example.calender.controller;

import com.example.calender.dto.CreateCalenderRequest;
import com.example.calender.dto.CreateCalenderResponse;
import com.example.calender.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
