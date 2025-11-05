package com.example.calender.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    private String calPwd;

    // 양방향 매핑
    @OneToMany(mappedBy = "calender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 댓글 개수 확인 (10개 제한)
    public int getCommentCount() {
        return comments.size();
    }

    // 댓글이 10개 미만인지 확인
    public boolean canAddComment() {
        return comments.size() < 10;
    }

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
    public Calender(String userName, String calTitle, String calContent, String calPwd) {
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
    public boolean isPasswordMatch(String rawPwd) {
        if (this.calPwd == null || rawPwd == null) {
            return false;
        }
        return this.calPwd.equals(rawPwd);
    }

}
