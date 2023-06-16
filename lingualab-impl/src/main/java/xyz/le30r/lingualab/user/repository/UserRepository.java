package xyz.le30r.lingualab.user.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.le30r.lingualab.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
