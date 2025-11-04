package com.example.calender.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "calenders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calender extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String userName;
    @Column(length = 30, nullable = false)
    private String calTitle;
    @Column(length = 200, nullable = false)
    private String calContent;
    @Column(nullable = false)
    private Integer calPwd;

    // 작성자 이름으로 조회할때 사용
    public Calender(String userName) {
        this.userName = userName;
    }

    // 비밀번호 포함하지 않음.
    public Calender(String userName, String calTitle, String calContent) {
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
    }

    // 비밀번호 포함함.
    public Calender(String userName, String calTitle, String calContent, Integer calPwd) {
        this.userName = userName;
        this.calTitle = calTitle;
        this.calContent = calContent;
        this.calPwd = calPwd;
    }

    /**
     * setter
     * 수정 가능한 필드만 반영하도록 update 메서드 구현
     * @param newUserName
     * @param newCalTitle
     */
    public void updateForTitleAndUser(String newUserName, String newCalTitle) {
        if (newUserName != null && !newUserName.isBlank()) {
            this.userName = newUserName;
        }
        if (newCalTitle != null && !newCalTitle.isBlank()) {
            this.calTitle = newCalTitle;
        }
    }

    // 비밀번호 검증
    public boolean isPasswordMatch(Integer rawPwd) {
        if (this.calPwd == null || rawPwd == null) {
            return false;
        }
        return this.calPwd.equals(rawPwd);
    }
}
