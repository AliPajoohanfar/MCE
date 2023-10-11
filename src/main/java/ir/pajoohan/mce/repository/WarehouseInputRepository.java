package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.WarehouseInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInputRepository extends JpaRepository<WarehouseInput, Long> {
}
