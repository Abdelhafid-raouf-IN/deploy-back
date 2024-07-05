package unibank.service.pilot.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unibank.service.pilot.domain.ApiEndpoint;

@Repository
public interface ApiEndpointRepository extends JpaRepository<ApiEndpoint, Long> {
    ApiEndpoint findByNameAndUrl(String name, String url);

}
