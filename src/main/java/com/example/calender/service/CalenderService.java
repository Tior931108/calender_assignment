package com.example.calender.service;

import com.example.calender.dto.*;
import com.example.calender.entity.Calender;
import com.example.calender.repository.CalenderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CalenderRepository calenderRepository;

    // 수정 메소드에서 수정일자가 첫 시도에는 반영이 안되고,
    // 두번째 시도에서 반영이 되는 문제점 발견.
    // @Transactional의 영속성 컨텍스트(1차 캐시)와 JPA Auditing(@LastModifiedDate) 간의 타이밍 문제
    @PersistenceContext
    private EntityManager entityManager; // 해결을 flush를 위한 주입

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
    public List<ReadUserNameCalenderResponse> findAll(String userName) {
        List<Calender> calenders;

        if (userName != null && !userName.isEmpty()) {
            // 작성자명으로 필터링
            calenders = calenderRepository.findByUserNameOrderByModifiedAtDesc(userName);
        } else {
            // 전체 조회
            calenders = calenderRepository.findAllByOrderByModifiedAtDesc();
        }

        List<ReadUserNameCalenderResponse> dtos = new ArrayList<>();
        for (Calender calender : calenders) {
            ReadUserNameCalenderResponse dto = new ReadUserNameCalenderResponse(
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
    public ReadOneCalenderResponse readOneCalender(Long id) {
        // fetch 조인문 사용
        Calender calender = calenderRepository.findByIdWithComments(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );

        // 단 건 조회시 댓글 포함하여 응답
        return ReadOneCalenderResponse.fromCalenderAndComments(calender);
    }

    // 수정
    @Transactional
    public UpdateCalenderResponse updateCalender(Long id, UpdateCalenderRequest updateCalenderRequest) {
        Calender calender = calenderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );

        // 비밀번호 검증
        if (!calender.isPasswordMatch(updateCalenderRequest.getCalPwd())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 요구사항:  작성일(createdAt)은 변경 불가. modifiedAt 는 Auditing에 의해 자동 갱신.
        calender.updateForTitleAndUser(updateCalenderRequest.getUserName(), updateCalenderRequest.getCalTitle());

        // Auditing의 modifiedAt 즉시 반영
        // 트랜잭션 내부에서 변경 직후 flush() Auditing이 즉시 반영되고, 리턴 시점에 modifiedAt이 갱신
        entityManager.flush();

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));

        if (!calender.isPasswordMatch(deleteCalPwd.getCalPwd())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        calenderRepository.delete(calender);
    }
}
