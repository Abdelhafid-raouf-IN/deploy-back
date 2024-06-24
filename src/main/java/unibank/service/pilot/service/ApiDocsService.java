package unibank.service.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibank.service.pilot.entity.ApiDocs;
import unibank.service.pilot.repository.ApiDocsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApiDocsService {

    @Autowired
    private ApiDocsRepository apiDocsRepository;

    public List<ApiDocs> getAllApiDocs() {
        return apiDocsRepository.findAll();
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
}
