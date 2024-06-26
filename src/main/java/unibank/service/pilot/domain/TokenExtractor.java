package unibank.service.pilot.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenExtractor {

    private final ObjectMapper objectMapper;

    public TokenExtractor() {
        this.objectMapper = new ObjectMapper();
    }

    public String extractTokenFromResponse(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
