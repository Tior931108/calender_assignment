package com.example.calender.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCalenderRequest {

    @NotBlank(message = "작성자명은 필수입니다.")
    @Size(max = 20, message = "작성자명은 최대 20자입니다.")
    private String userName; // 작성자명
    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 30, message = "일정 제목은 최대 30자입니다.")
    private String calTitle; // 일정 제목

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자만 입력해야 합니다.")
    private String calPwd; // 비밀번호
}
