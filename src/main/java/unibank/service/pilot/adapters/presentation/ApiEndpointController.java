package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.domain.ApiEndpoint;
import unibank.service.pilot.services.ApiEndpointService;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
@CrossOrigin(origins = "http://localhost:3001")
@Tag(name = "Endpoints", description = "Controller for API endpoint management")
public class ApiEndpointController {

    private final ApiEndpointService apiEndpointService;

    public ApiEndpointController(ApiEndpointService apiEndpointService) {
        this.apiEndpointService = apiEndpointService;
    }

    @Operation(summary = "Get all API endpoints")
    @GetMapping
    public List<ApiEndpoint> getAllApiEndpoints() {
        return apiEndpointService.getAllApiEndpoints();
    }

    @Operation(summary = "Create new API endpoint")
    @PostMapping("/create")
    public ResponseEntity<ApiEndpoint> createApiEndpoint(@RequestBody ApiEndpoint apiEndpoint) {
        ApiEndpoint createdApiEndpoint = apiEndpointService.saveApiEndpoint(apiEndpoint);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApiEndpoint);
    }

    @Operation(summary = "Get an API endpoint by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiEndpoint> getApiEndpointById(@PathVariable Long id) {
        ApiEndpoint apiEndpoint = apiEndpointService.getApiEndpointById(id);
        return ResponseEntity.ok(apiEndpoint);
    }

    @Operation(summary = "Update an API endpoint by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiEndpoint> updateApiEndpoint(@PathVariable Long id, @RequestBody ApiEndpoint apiEndpointDetails) {
        ApiEndpoint updatedApiEndpoint = apiEndpointService.updateApiEndpoint(id, apiEndpointDetails);
        return ResponseEntity.ok(updatedApiEndpoint);
    }

    @Operation(summary = "Delete an API endpoint by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApiEndpoint(@PathVariable Long id) {
        apiEndpointService.deleteApiEndpoint(id);
        return ResponseEntity.noContent().build();
    }
}