package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.PreDeliveryControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreDeliveryControlRepository extends JpaRepository<PreDeliveryControl, Long> {
}