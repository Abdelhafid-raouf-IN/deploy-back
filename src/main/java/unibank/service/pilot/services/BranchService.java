package unibank.service.pilot.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.adapters.persistence.BranchRepository;
import unibank.service.pilot.domain.Branch;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }
    public List<Branch> saveBranches(List<Branch> branches) {
        return branchRepository.saveAll(branches);
    }
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
