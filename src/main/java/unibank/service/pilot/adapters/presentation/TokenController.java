package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.services.AuthService;

@RestController
@RequestMapping("/api/token")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Token", description = "Controller for token generation")
public class TokenController {

    private final AuthService authService;

    public TokenController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Get token for a specific environment and branch")
    @GetMapping
    public String getToken(@RequestParam String environment, @RequestParam String branch) {
        try {
            return authService.getToken(environment, branch);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
