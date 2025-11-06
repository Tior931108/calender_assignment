# �Calender Assignment

Spring boot를 활용한 일정 프로젝트 과제입니다.

## 📋 프로젝트 개요

이 프로젝트는 Spring boot를 이용하여 RESTful API를 구현한 일정관리 프로그램 입니다.

## 🎯 주요 기능

### [필수] Lv.1 - 일정 생성
- `일정 제목`, `일정 내용`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
- `작성/수정일`은 날짜와 시간을 모두 포함한 형태
- 최초 생성 시, `수정일`은 `작성일`과 동일
- `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용
- API 응답에 `비밀번호`는 제외해야함

### [필수] Lv.2 - 일정 조회 + [도전] Lv.6 - 일정 단건 조회시 댓글 함께 응답

**전체 일정 조회**
- `작성자명`을 기준으로 등록된 일정 목록을 전부 조회
- `작성자명`은 조회 조건으로 포함될 수도 있고, 포함되지 않을 수도 있음.
- 하나의 API로 작성해야함
- `수정일` 기준 내림차순 정렬
- API 응답에 `비밀번호`는 제외해야함
  
**선택 일정 조회**
- 선택한 일정 단건의 정보 조회
- 일정의 고유 식별자(ID)를 사용하여 조회
- API 응답에 `비밀번호`는 제외해야함
- 일정 단건 조회시 해당 일정에 등록된 댓글들을 포함하여 함께 응답함.

### [필수] Lv.3 - 일정 수정
- 선택한 일정 내용 중 `일정 제목`, `작성자명`만 수정 가능
- 서버에 일정 수정을 요청할 때 비밀번호`를 함께 전달함
- `작성일`은 변경할 수 없으며, `수정일`은 수정 완료시, 수정한 시점으로 변경되어야함
- API 응답에 `비밀번호`는 제외해야함

### [필수] Lv.4 - 일정 삭제
- 선택한 일정 삭제
- 서버에 일정 삭제를 요청할 때 `비밀번호`를 함께 전달함.

### [도전] Lv.5 - 댓글 작성
- 일정에 댓글 작성 및 생성시 댓글 내용, 작성자명, 비밀번호, 작성/수정일, 일정 고유식별자(ID) 포함
- 하나의 일정에는 댓글을 10개까지만 작성가능
- API 응답에 `비밀번호` 제외

### [도전] Lv.7 - 유저의 입력에 대한 검증 수행
- 잘못된 입력이나 요청을 방지 하여 데이터의 `무결성을 보장`함.
- `일정 제목`은 최대 30자 이내로 제한, 필수값 처리
- `일정 내용`은 최대 200자 이내로 제한, 필수값 처리
- `댓글 내용`은 최대 100자 이내로 제한, 필수값 처리=
- `비밀번호`, 작성자명은 필수값 처리
- `비밀번호`가 일치하지 않을 경우 적절한 오류 코드 및 메시지 반환


## 🏗️ 프로젝트 구조
```
src/main/java/com/example/calender
├──controller
   └── CalenderController.java            # calender Controller 
   └── CommentController.java             # comment Controller 
├──dto
   └── CommentResponse.java               # comment 응답 DTO
   └── CreateCalenderRequest.java         # calender 일정 생성 요청 DTO
   └── CreateCalenderResponse.java        # calender 일정 생성 응답 DTO
   └── CreateCommentRequest.java          # comment 생성 요청 DTO
   └── DeleteCalPwdRequest.java           # calender 삭제 요청 DTO
   └── ReadOneCalenderResponse.java       # calender 일정 단건 응답 DTO 
   └── ReadUserNameCalenderResponse.java  # calender 일정 전체 응답 DTO 
   └── UpdateCalenderRequest.java         # calender 일정 수정 요청 DTO
   └── UpdateCalenderResponse.java        # calender 일정 수정 응답 DTO 
├──entity
   └── BaseEntity.java                    # 작성일/수정일 JPA Auditing 
   └── Calender.java                      # calender 엔티티  
   └── Comment.java                       # comment 엔티티
├──exception
   └── GlobalExceptionHandler.java        # 전역 에러 관리 
├──repository
   └── CalenderRepository.java            # calender repository
   └── CommentRepository.java             # comment repository
├──service
   └── CalenderService.java               # calender service
   └── CommentService.java                # comment service
CalenderApplication.java                  # calender project main
```

## 📝 API 명세서

| **API** | **HTTP method** | **URL** |
| --- | --- | --- |
| 일정 생성 | POST | /caleders |
| 일정 전체 조회 | GET | /caleders |
| 일정 필터링 조회 | GET | /calenders?userName=xxx |
| 일정 단건 조회 | GET | /calenders/{id} |
| 일정 수정 | PATCH | /calenders/{id} |
| 일정 삭제 | DELETE | /calenders/{id} |
| 댓글 생성 | POST | /calenders/{calId}/comments |
| 댓글 전체 조회 | GET | /calenders/{calId}/comments |

**https://documenter.getpostman.com/view/49691093/2sB3WqvLtK**

## 🧑‍🏫 ERD
<img width="1258" height="269" alt="image" src="https://github.com/user-attachments/assets/8a063d5b-f328-4637-b8d4-0ec047c756a4" />

## 📚 학습 내용

- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발
- `controller` : HTTP 요청 매핑, 요청 파라미터 검증, 응답 데이터 변환, 예외 처리 및 에러 응답
- `service` : 비즈니스 규칙 구현, 데이터 유효성 검증, 여러 데이터 소스 조합, 트랜잭션 경계 설정
- `repository` : CRUD 연산 구현, 쿼리 최적화, 데이터 매핑
- `JPA Auditing` : 엔티티의 성성 및 수겅 시간을 자동으로 관리 `@CreateDate` , `@LastModifiedDate`
- `Calender` - `Comment` 간 연관관계 매핑 (`@OneToMany`, `@ManyToOne`)
- 엔티티간 조인 사용시 N+1 문제 발생 > `Fetch join`으로 최적화
- `validation` 의존성 추가로 유효성 검사 기능 활용
- Request DTO 필드에 `@NotBlank`, `@Size` 등 유효성 검사 어노테이션 적용
- Controller 메서드에 `@Valid` 적용
- `@ExceptionHandler` 또는 `@ControllerAdvice`를 사용한 전역 예외 처리 구현

## 🔄 버전 히스토리

- **v1.0**: API 문서 및 ERD 작성
- **v2.0**: 일정 CRUD 구현
- **v3.0**: 댓글 생성 기능 구현
- **v3.1**: 일정 단건 조회시 관련 댓글 모두 조회하도록 기능 추가
- **v3.2**: 유효성 검사(Validation) 및 예외 처리

## 👤 작성자

Tior931108 Ch.3 Spring 입문 주차 3조 꾸러기들 정용준
