package unibank.service.pilot.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.domain.BranchCredential;

import java.util.Optional;

public interface BranchCredentialRepository extends JpaRepository<BranchCredential, Long> {
    Optional<BranchCredential> findByEnvironmentAndBranch(String environment, String branch);

}
