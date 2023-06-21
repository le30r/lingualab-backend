package xyz.le30r.lingualab.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invite")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdAt;
    private Instant expiresAt;
    private Integer usages;
    private String link;

    @ManyToOne(targetEntity = Auth.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Auth teacher;
    @Column(name = "teacher_id")
    private Long teacherId;
}
