package com.example.calender.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DeleteCalPwdRequest {

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자만 입력해야 합니다.")
    private String calPwd;
}
