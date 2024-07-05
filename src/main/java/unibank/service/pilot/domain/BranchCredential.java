package unibank.service.pilot.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "branch_credential")
public class BranchCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "auth_url")
    private String authUrl;
    @Column(name = "\"authorization\"") // Quote the column name
    private String authorization;
    @Column(name = "branch")
    private String branch;
    @Column(name = "environment")
    private String environment;
    @Column(name = "grant_type")
    private String grantType;
    @Column(name = "password")
    private String password;
    @Column(name = "scope")
    private String scope;
    @Column(name = "username")
    private String username;
    public BranchCredential(Long id, String environment, String branch, String authorization, String username, String password, String scope, String grantType, String authUrl) {
        this.id = id;
        this.environment = environment;
        this.branch = branch;
        this.authorization = authorization;
        this.username = username;
        this.password = password;
        this.scope = scope;
        this.grantType = grantType;
        this.authUrl = authUrl;
    }
    public BranchCredential() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getAuthorization() {
        return authorization;
    }
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getGrantType() {
        return grantType;
    }
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    public String getAuthUrl() {
        return authUrl;
    }
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }
}