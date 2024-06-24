package unibank.service.pilot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.entity.ApiDocs;

public interface ApiDocsRepository extends JpaRepository<ApiDocs, Long> {
}

