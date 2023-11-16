package shop.ssap.ssap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.ssap.ssap.domain.UserTest;

import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    Optional<UserTest> findByName(String name);
}
