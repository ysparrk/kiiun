package com.cx_project.kiiun.domain.growth.entity;

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

@Entity
@Table(name = "growth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE growth SET deleted_at = now() WHERE id = ?")
public class Growth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "weight")
    private float weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lizard_id", nullable = false)
    private Lizard lizard;

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

    @Builder
    public Growth(Long id, float weight, Lizard lizard, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.weight = weight;
        this.lizard = lizard;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }
}
