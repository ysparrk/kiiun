package com.cx_project.kiiun.domain.member.entity;

import com.cx_project.kiiun.domain.growth.entity.Growth;
import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE member SET deleted_at = now() WHERE id = ?")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "nickname", length = 12, nullable = false)
    private String nickname;

    @Column(name = "profile_url", length = 255)
    private String profileUrl;

    //TODO: 주소 데이터 입력 방식 상의
    @Column(name = "locate")
    private String locate;

    //TODO: Lizard 테이블과의 양방향 매핑이 필요한지 고민

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ColumnDefault("false")
    @Column(name = "is_active")
    private Boolean isActive=false;  //기본 정보 입력 후 회원 가입 확정

    @Builder
    public Member(Long id, String email, String nickname, String profileUrl, String locate, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDeleted, LocalDateTime deletedAt, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.locate = locate;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.isActive = isActive;
    }
}
