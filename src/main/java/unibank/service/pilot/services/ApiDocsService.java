package unibank.service.pilot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.adapters.persistence.ApiDocsRepository;
import unibank.service.pilot.domain.ApiDocs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiDocsService {

    @Autowired
    private ApiDocsRepository apiDocsRepository;
    public List<ApiDocs> getAllApiDocs() {
        List<ApiDocs> apiDocsList = apiDocsRepository.findAll();
        return apiDocsList.stream()
                .map(this::validateApiUrl)
                .collect(Collectors.toList());
    }
    public Optional<ApiDocs> getApiDocsById(Long id) {
        return apiDocsRepository.findById(id);
    }
    public ApiDocs saveApiDocs(ApiDocs apiDocs) {
        return apiDocsRepository.save(apiDocs);
    }
    public void deleteApiDocs(Long id) {
        apiDocsRepository.deleteById(id);
    }
    public ApiDocs patchApiDocs(Long id, Map<String, Object> updates) {
        ApiDocs existingApiDocs = getApiDocsById(id)
                .orElseThrow(() -> new RuntimeException("API documentation not found"));

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

        return saveApiDocs(existingApiDocs);
    }

    private ApiDocs validateApiUrl(ApiDocs apiDoc) {
        try {
            URL url = new URL(apiDoc.getUrl());
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            huc.connect();
            int responseCode = huc.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Invalid URL: " + apiDoc.getUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiDoc;
    }
}