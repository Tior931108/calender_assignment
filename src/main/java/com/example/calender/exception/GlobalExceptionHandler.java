package com.example.calender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation 실패 (400 Bad request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        // 에러 응답 Map 저장
        Map<String, Object> errorResponse = new HashMap<>();
        // validaion 유효성 검사 메시지 저장
        Map<String, String> errors = new HashMap<>();

        // 모든 필드 에러 수집
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // 상태 코드 표시
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        // validation 유효성 검사 내용
        errorResponse.put("errors", errors);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 리소스를 찾을 수 없음 (404 Not found)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        // 에러 응답 저장
        Map<String, Object> errorResponse = new HashMap<>();
        // 상태 코드 표시
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        // validation 유효성 검사 내용
        errorResponse.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // 비즈니스 로직 위반 (400 Bad request)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalStateException(IllegalStateException e) {
        // 에러 응답 저장
        Map<String, Object> errorResponse = new HashMap<>();
        // 상태 코드 표시
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        // validation 유효성 검사 내용
        errorResponse.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 기타 모든 예외 (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception e) {
        // 에러 응답 저장
        Map<String, Object> errorResponse = new HashMap<>();
        // 상태 코드 표시
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        // 오류 출력
        errorResponse.put("message", "서버 내부 오류가 발생했습니다.");

        // 개발에서 상세 에러 표시
        // errorResponse.put("details", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
