package xyz.le30r.lingualab.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.time.Instant;
import java.util.Objects;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    Role role;
    Instant createdAt;
    Instant expiresAt;

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false;

        return Objects.equals(id, ((Auth) other).id);
    }
}
