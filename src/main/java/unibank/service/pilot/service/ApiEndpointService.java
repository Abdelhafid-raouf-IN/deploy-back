package unibank.service.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.entity.ApiEndpoint;
import unibank.service.pilot.repository.ApiEndpointRepository;

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
