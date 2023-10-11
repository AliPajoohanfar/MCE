package ir.pajoohan.mce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineTypeRepository extends JpaRepository<EngineTypeRepository, Long> {
}