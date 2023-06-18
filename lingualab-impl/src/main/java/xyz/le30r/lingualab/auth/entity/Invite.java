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
    @ManyToOne(targetEntity = Auth.class)
    @JoinColumn(name = "teacher_id")
    private Long teacher;
    private Instant createdAt;
    private Instant expiresAt;
    private Integer usages;
    private String link;





}
