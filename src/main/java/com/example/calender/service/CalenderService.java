package com.example.calender.service;

import com.example.calender.dto.CreateCalenderRequest;
import com.example.calender.dto.CreateCalenderResponse;
import com.example.calender.dto.GetUserNameCalenderResponse;
import com.example.calender.entity.Calender;
import com.example.calender.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    // 저장
    @Transactional
    public CreateCalenderResponse save(CreateCalenderRequest createCalenderRequest) {

        // 요청으로 들어온 속성값
        Calender calender = new Calender(
                createCalenderRequest.getUserName(),
                createCalenderRequest.getCalTitle(),
                createCalenderRequest.getCalContent(),
                createCalenderRequest.getCalPwd()
        );

        // repository 에서 save 저장 처리
        Calender savedCalender = calenderRepository.save(calender);

        // conroller로 반환
        return new CreateCalenderResponse(
                savedCalender.getId(),
                savedCalender.getUserName(),
                savedCalender.getCalTitle(),
                savedCalender.getCalContent(),
                savedCalender.getCreatedAt(),
                savedCalender.getModifiedAt()
        );

    }
}
