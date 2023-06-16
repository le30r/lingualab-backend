package xyz.le30r.lingualab.user;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import xyz.le30r.lingualab.auth.entity.Auth;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "user_info")
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    private String name;
    private String surname;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User teacher;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Auth auth;

}
