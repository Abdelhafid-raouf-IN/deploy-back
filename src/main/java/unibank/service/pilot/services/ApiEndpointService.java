package unibank.service.pilot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.adapters.persistence.ApiEndpointRepository;
import unibank.service.pilot.domain.ApiEndpoint;
import unibank.service.pilot.exeptions.ResourceNotFoundException;

import java.util.List;

@Service
public class ApiEndpointService {
    private final ApiEndpointRepository apiEndpointRepository;

    @Autowired
    public ApiEndpointService(ApiEndpointRepository apiEndpointRepository) {
        this.apiEndpointRepository = apiEndpointRepository;
    }

    public ApiEndpoint saveApiEndpoint(ApiEndpoint apiEndpoint) {
        return apiEndpointRepository.save(apiEndpoint);
    }

    public List<ApiEndpoint> getAllApiEndpoints() {
        return apiEndpointRepository.findAll();
    }

    public ApiEndpoint getApiEndpointById(Long id) {
        return apiEndpointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ApiEndpoint not found with id " + id));
    }

    public ApiEndpoint updateApiEndpoint(Long id, ApiEndpoint apiEndpointDetails) {
        ApiEndpoint apiEndpoint = getApiEndpointById(id);
        apiEndpoint.setName(apiEndpointDetails.getName());
        apiEndpoint.setUrl(apiEndpointDetails.getUrl());
        apiEndpoint.setEnvironment(apiEndpointDetails.getEnvironment());
        return apiEndpointRepository.save(apiEndpoint);
    }

    public void deleteApiEndpoint(Long id) {
        ApiEndpoint apiEndpoint = getApiEndpointById(id);
        apiEndpointRepository.delete(apiEndpoint);
    }
}