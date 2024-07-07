package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.domain.Branch;
import unibank.service.pilot.services.BranchService;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "filiale", description = "Controller for API filiale management")
public class ApiBranchesController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/save")
    public List<Branch> saveBranches(@RequestBody List<Branch> branches) {
        return branchService.saveBranches(branches);
    }
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }
}
