package shop.ssap.ssap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleDataRepository extends JpaRepository<SampleData, Integer> {
    Optional<SampleData> findByDetail(String detail);
}
