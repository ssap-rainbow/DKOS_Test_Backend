package shop.ssap.ssap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ssap.ssap.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderId(String providerId);

    Optional<User> findByEmail(String email);
}