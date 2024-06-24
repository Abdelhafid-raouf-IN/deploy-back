package unibank.service.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.entity.ApiDocs;
import unibank.service.pilot.entity.User;
import unibank.service.pilot.service.ApiDocsService;
import unibank.service.pilot.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Autoriser les requêtes depuis le frontend React
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiDocsService apiDocsService;

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
        response.put("Service", "unibank.service.pilot");
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

}
