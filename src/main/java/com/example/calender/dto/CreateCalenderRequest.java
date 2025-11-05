package com.example.calender.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CreateCalenderRequest {

    @NotBlank(message = "작성자명은 필수입니다.")
    @Size(max = 20, message = "작성자명은 최대 20자입니다.")
    private String userName;

    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 30, message = "일정 제목은 최대 30자입니다.")
    private String calTitle;

    @NotBlank(message = "일정 내용은 필수입니다.")
    @Size(max = 30, message = "일정 내용은 최대 200자입니다.")
    private String calContent;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자만 입력해야 합니다.")
    private String calPwd;
}
