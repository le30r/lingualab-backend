package xyz.le30r.lingualab.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.le30r.lingualab.auth.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByLogin(String login);
}