package unibank.service.pilot.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "apis")
public class ApiDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String baseUrl;

    public ApiDocs() {
    }
    public ApiDocs(String name, String url, String baseUrl) {
        this.name = name;
        this.url = url;
        this.baseUrl = baseUrl;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    @Override
    public String toString() {
        return "ApiDocs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
