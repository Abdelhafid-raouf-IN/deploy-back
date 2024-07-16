package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.adapters.persistence.ApiTestResultRepository;
import unibank.service.pilot.domain.ApiTestResult;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http://localhost:3001")
@Tag(name = "Test-Result", description = "Controller for API test result management")
public class ApiTestResultController {

    private final ApiTestResultRepository apiTestResultRepository;

    public ApiTestResultController(ApiTestResultRepository apiTestResultRepository) {
        this.apiTestResultRepository = apiTestResultRepository;
    }

    @Operation(summary = "Get all API test results")
    @GetMapping("/results")
    public ResponseEntity<List<ApiTestResult>> getAllApiTestResults() {
        try {
            List<ApiTestResult> testResults = apiTestResultRepository.findAll();
            if (testResults.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Save API test result")
    @PostMapping("/save" )
    public ResponseEntity<String> saveTestResult(@RequestBody ApiTestResult testResult) {
        try {
            System.out.println("Received test result: " + testResult.toString());
            apiTestResultRepository.save(testResult);
            return ResponseEntity.ok("Test result saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save test result: " + e.getMessage());
        }
    }
}
