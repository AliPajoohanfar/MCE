package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.MotorcycleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleTypeRepository extends JpaRepository<MotorcycleType, Long> {
}
