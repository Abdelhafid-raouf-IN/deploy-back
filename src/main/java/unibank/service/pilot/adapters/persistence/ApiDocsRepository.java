package unibank.service.pilot.adapters.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.domain.ApiDocs;

public interface ApiDocsRepository extends JpaRepository<ApiDocs, Long> {
}

