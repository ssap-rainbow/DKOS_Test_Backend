package shop.ssap.ssap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.ssap.ssap.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
