package unibank.service.pilot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.domain.ApiEndpoint;
import unibank.service.pilot.adapters.persistence.ApiEndpointRepository;

import java.util.List;

@Service
public class ApiEndpointService {
    private final ApiEndpointRepository apiEndpointRepository;
    @Autowired
    public ApiEndpointService(ApiEndpointRepository apiEndpointRepository) {
        this.apiEndpointRepository = apiEndpointRepository;
    }
    public List<ApiEndpoint> getAllApiEndpoints() {
        return apiEndpointRepository.findAll();
    }
}
