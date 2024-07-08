package unibank.service.pilot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

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
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private long duration;
    private LocalDateTime localDate;
    private String environment;
    private String branch;

    public ApiTestResult(Long id, String apiName, String endpoint, String method, String status, String responseBody, String responseHeaders, LocalDateTime startTime, LocalDateTime stopTime, long duration, LocalDateTime localDate, String environment, String branch) {
        this.id = id;
        this.apiName = apiName;
        this.endpoint = endpoint;
        this.method = method;
        this.status = status;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.duration = duration;
        this.localDate = localDate;
        this.environment = environment;
        this.branch = branch;
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
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getLocalDate() {
        return localDate;
    }
    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public LocalDateTime getStopTime() {
        return stopTime;
    }
    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
}