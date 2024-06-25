package unibank.service.pilot.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
