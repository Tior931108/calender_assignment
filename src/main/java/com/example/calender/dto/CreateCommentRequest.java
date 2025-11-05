package com.example.calender.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotBlank(message = "작성자명은 필수입니다.")
    @Size(max = 20, message = "작성자명은 최대 20자입니다.")
    private String commentName;
    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 30, message = "댓글 내용은 최대 100자입니다.")
    private String comContent;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "\\d{4}", message = "비밀번호는 4자리 숫자만 입력해야 합니다.")
    private String calPwd;
}
