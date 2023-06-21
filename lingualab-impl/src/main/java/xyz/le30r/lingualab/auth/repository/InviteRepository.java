package xyz.le30r.lingualab.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.le30r.lingualab.auth.entity.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
}