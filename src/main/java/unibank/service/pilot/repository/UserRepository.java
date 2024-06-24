package unibank.service.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
