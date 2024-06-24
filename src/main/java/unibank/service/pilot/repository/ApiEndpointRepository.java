package unibank.service.pilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unibank.service.pilot.entity.ApiEndpoint;

@Repository
public interface ApiEndpointRepository extends JpaRepository<ApiEndpoint, Long> {
}
