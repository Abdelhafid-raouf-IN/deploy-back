package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Healthcheck", description = "Controller for service status")
public class StatusController {

    @Operation(summary = "Get service status")
    @GetMapping
    public Map<String, String> status() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "unibank.service.pilot");
        response.put("version", "2.0.0");
        return response;
    }
}
