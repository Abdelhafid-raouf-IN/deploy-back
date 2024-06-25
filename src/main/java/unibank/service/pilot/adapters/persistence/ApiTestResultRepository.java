package unibank.service.pilot.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.domain.ApiTestResult;

public interface ApiTestResultRepository extends JpaRepository<ApiTestResult, Long> {
}
