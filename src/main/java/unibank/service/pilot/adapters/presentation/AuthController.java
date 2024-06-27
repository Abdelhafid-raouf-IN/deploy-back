package unibank.service.pilot.adapters.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.adapters.persistence.ApiTestResultRepository;
import unibank.service.pilot.domain.ApiDocs;
import unibank.service.pilot.domain.ApiEndpoint;
import unibank.service.pilot.domain.ApiTestResult;
import unibank.service.pilot.domain.User;
import unibank.service.pilot.services.ApiDocsService;
import unibank.service.pilot.services.ApiEndpointService;
import unibank.service.pilot.services.AuthService;
import unibank.service.pilot.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*test-pull*/
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApiDocsService apiDocsService;
    @Autowired
    private ApiEndpointService apiEndpointService;
    @Autowired
    private ApiTestResultRepository apiTestResultRepository;
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already in use!");
        }
        return ResponseEntity.ok(userService.save(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser == null || !new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials!");
        }
        return ResponseEntity.ok("Login successful");
    }
    @GetMapping("/status")
    public Map<String, String> status() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "unibank.service.pilot");
        response.put("version", "2.0.0");
        return response;
    }
    @GetMapping("/apis")
    public ResponseEntity<List<ApiDocs>> getAllApiDocs() {
        List<ApiDocs> apiDocsList = apiDocsService.getAllApiDocs();
        return ResponseEntity.ok(apiDocsList);
    }
    @PostMapping
    public ResponseEntity<ApiDocs> createApiDocs(@RequestBody ApiDocs apiDocs) {
        ApiDocs createdApiDocs = apiDocsService.saveApiDocs(apiDocs);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApiDocs);
    }
    @GetMapping("/endpoints")
    public List<ApiEndpoint> getAllApiEndpoints() {
        return apiEndpointService.getAllApiEndpoints();
    }
    @PostMapping("/save")
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
    @GetMapping("/token")
    public String getToken(@RequestParam String environment, @RequestParam String branch) {
        try {
            return authService.getToken(environment, branch);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
