package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineTypeRepository extends JpaRepository<EngineType, Long> {
}