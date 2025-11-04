package com.example.calender.service;

import com.example.calender.dto.*;
import com.example.calender.entity.Calender;
import com.example.calender.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    // 전체 조회 (작성자명 필터링)
    @Transactional(readOnly = true)
    public List<GetUserNameCalenderResponse> findAll(String userName) {
        List<Calender> calenders;

        if(userName != null && !userName.isEmpty()) {
            // 작성자명으로 필터링
            calenders = calenderRepository.findByUserNameOrderByModifiedAtDesc(userName);
        } else {
            // 전체 조회
            calenders = calenderRepository.findAllByOrderByModifiedAtDesc();
        }

        List<GetUserNameCalenderResponse> dtos = new ArrayList<>();
        for (Calender calender : calenders) {
            GetUserNameCalenderResponse dto = new GetUserNameCalenderResponse(
                    calender.getId(),
                    calender.getUserName(),
                    calender.getCalTitle(),
                    calender.getCalContent(),
                    calender.getCreatedAt(),
                    calender.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 선택 일정 조회 (단 건 조회)
    @Transactional(readOnly = true)
    public GetOneCalenderResponse getOneCalender(Long id) {
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return new GetOneCalenderResponse(
                calender.getId(),
                calender.getUserName(),
                calender.getCalTitle(),
                calender.getCalContent(),
                calender.getCreatedAt(),
                calender.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public UpdateCalenderResponse updateCalender(Long id, UpdateCalenderRequest updateCalenderRequest) {
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        // 비밀번호 검증
        if (!calender.isPasswordMatch(updateCalenderRequest.getCalPwd())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 요구사항:  작성일(createdAt)은 변경 불가. modifiedAt 는 Auditing에 의해 자동 갱신.
        calender.updateForTitleAndUser(updateCalenderRequest.getUserName(), updateCalenderRequest.getCalTitle());

        // 응답 DTO
        return new UpdateCalenderResponse(
                calender.getId(),
                calender.getUserName(),
                calender.getCalTitle(),
                calender.getCalContent(),
                calender.getCreatedAt(),
                calender.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void deleteCalender(Long id, DeleteCalPwdRequest deleteCalPwd) {
        Calender calender = calenderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다. id=" + id));

        if (!calender.isPasswordMatch(deleteCalPwd.getCalPwd())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        calenderRepository.delete(calender);
    }
}
