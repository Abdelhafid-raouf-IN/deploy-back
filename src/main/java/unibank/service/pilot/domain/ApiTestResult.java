package unibank.service.pilot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ApiTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiName;
    private String endpoint;
    private String method;
    private String status;
    private String responseBody;
    private String responseHeaders;

    public ApiTestResult(Long id, String apiName, String endpoint, String method, String status, String responseBody, String responseHeaders) {
        this.id = id;
        this.apiName = apiName;
        this.endpoint = endpoint;
        this.method = method;
        this.status = status;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
    }

    public ApiTestResult() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getResponseBody() {
        return responseBody;
    }
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    public String getResponseHeaders() {
        return responseHeaders;
    }
    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

}
