package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import unibank.service.pilot.domain.User;
import unibank.service.pilot.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Authentication", description = "Controller for authentication and API management")
public class AuthController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already in use!");
        }
        return ResponseEntity.ok(userService.save(user));
    }
    @Operation(summary = "Login a user")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = userService.findByUsername(user.getUsername());
        if (foundUser == null || !new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials!");
        }
        return ResponseEntity.ok("Login successful");
    }
    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @Operation(summary = "Get user by ID")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
