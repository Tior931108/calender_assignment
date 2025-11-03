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


}
