package xyz.le30r.lingualab.user.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.le30r.lingualab.user.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByAuth_Login(String username);
}
