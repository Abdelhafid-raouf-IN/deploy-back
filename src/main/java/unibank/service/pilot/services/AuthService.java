// src/main/java/com/example/api/services/AuthService.java
package unibank.service.pilot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unibank.service.pilot.adapters.persistence.BranchCredentialRepository;
import unibank.service.pilot.domain.BranchCredential;
import unibank.service.pilot.domain.TokenExtractor;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private BranchCredentialRepository branchCredentialRepository;

    public String getToken(String environment, String branch) throws Exception {
        Optional<BranchCredential> credentialOpt = branchCredentialRepository.findByEnvironmentAndBranch(environment, branch);
        if (credentialOpt.isPresent()) {
            BranchCredential credential = credentialOpt.get();
            String authUrl = credential.getAuthUrl();
            String authorization = credential.getAuthorization();
            String username = credential.getUsername();
            String password = credential.getPassword();
            String scope = credential.getScope();
            String grantType = credential.getGrantType();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorization);
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            String body = String.format("grant_type=%s&username=%s&password=%s&scope=%s", grantType, username, password, scope);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(authUrl, HttpMethod.POST, entity, String.class);

            // Assuming the response contains the token in a JSON object with key 'access_token'
            String token = extractTokenFromResponse(response.getBody());
            return token;
        } else {
            throw new Exception("Credentials not found for environment: " + environment + " and branch: " + branch);
        }
    }
    private String extractTokenFromResponse(String responseBody) {
        TokenExtractor tokenExtractor = new TokenExtractor();
        String token = tokenExtractor.extractTokenFromResponse(responseBody);
        System.out.println("Extracted token: " + token);
        return "token";
    }
}