package unibank.service.pilot.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unibank.service.pilot.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
