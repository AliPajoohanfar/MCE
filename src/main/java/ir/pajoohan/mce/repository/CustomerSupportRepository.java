package ir.pajoohan.mce.repository;

import ir.pajoohan.mce.entity.CustomerSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long> {
}
