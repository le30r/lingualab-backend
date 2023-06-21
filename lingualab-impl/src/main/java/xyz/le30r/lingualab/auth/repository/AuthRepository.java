package xyz.le30r.lingualab.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.le30r.lingualab.auth.entity.Auth;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByLogin(String login);
}