package com.example.calender.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String commentName;
    @Column(length = 100, nullable = false)
    private String comContent;
    @Column(nullable = false)
    private Integer comPwd;

    // 외래키(FK) : 일정 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calId", nullable = false)
    private Calender calender;

    // 비밀번호 포함한 생성자
    public Comment(String commentName, String comContent, Integer comPwd, Calender calender) {
        this.commentName = commentName;
        this.comContent = comContent;
        this.comPwd = comPwd;
        this.calender = calender;
    }

    // 비밀번호 포함하지 않음
    public Comment(String commentName, String comContent, Calender calender) {
        this.commentName = commentName;
        this.comContent = comContent;
        this.calender = calender;
    }

}
