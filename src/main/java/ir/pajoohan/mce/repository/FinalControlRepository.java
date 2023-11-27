package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.FinalControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalControlRepository extends JpaRepository<FinalControl, Long> {
}
