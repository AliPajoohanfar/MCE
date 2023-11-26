package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.AftersalesService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AftersalesServiceRepository extends JpaRepository<AftersalesService, Long> {
}
