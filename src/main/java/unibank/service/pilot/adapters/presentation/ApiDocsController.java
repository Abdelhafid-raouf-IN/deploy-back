package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.domain.ApiDocs;
import unibank.service.pilot.services.ApiDocsService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/docs")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Documentation", description = "Controller for API documentation management")
public class ApiDocsController {

    private final ApiDocsService apiDocsService;

    public ApiDocsController(ApiDocsService apiDocsService) {
        this.apiDocsService = apiDocsService;
    }

    @Operation(summary = "Get all API documentation")
    @GetMapping
    public ResponseEntity<List<ApiDocs>> getAllApiDocs() {
        List<ApiDocs> apiDocsList = apiDocsService.getAllApiDocs();
        return ResponseEntity.ok(apiDocsList);
    }

    @Operation(summary = "Get API documentation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiDocs> getApiDocsById(@PathVariable Long id) {
        Optional<ApiDocs> apiDocs = apiDocsService.getApiDocsById(id);
        return apiDocs.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create new API documentation")
    @PostMapping
    public ResponseEntity<ApiDocs> createApiDocs(@RequestBody ApiDocs apiDocs) {
        ApiDocs createdApiDocs = apiDocsService.saveApiDocs(apiDocs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApiDocs);
    }

    @Operation(summary = "Update existing API documentation")
    @PutMapping("/{id}")
    public ResponseEntity<ApiDocs> updateApiDocs(@PathVariable Long id, @RequestBody ApiDocs apiDocs) {
        if (!apiDocsService.getApiDocsById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        apiDocs.setId(id);
        ApiDocs updatedApiDocs = apiDocsService.saveApiDocs(apiDocs);
        return ResponseEntity.ok(updatedApiDocs);
    }

    @Operation(summary = "Partially update API documentation")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiDocs> patchApiDocs(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<ApiDocs> optionalApiDocs = apiDocsService.getApiDocsById(id);
        if (optionalApiDocs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ApiDocs existingApiDocs = optionalApiDocs.get();
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingApiDocs.setName((String) value);
                    break;
                case "url":
                    existingApiDocs.setUrl((String) value);
                    break;
                case "baseUrl":
                    existingApiDocs.setBaseUrl((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        ApiDocs updatedApiDocs = apiDocsService.saveApiDocs(existingApiDocs);
        return ResponseEntity.ok(updatedApiDocs);
    }

    @Operation(summary = "Delete API documentation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApiDocs(@PathVariable Long id) {
        if (!apiDocsService.getApiDocsById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        apiDocsService.deleteApiDocs(id);
        return ResponseEntity.noContent().build();
    }
}
