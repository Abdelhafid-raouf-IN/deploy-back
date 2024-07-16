package unibank.service.pilot.adapters.presentation;

import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator/metrics")
@CrossOrigin(origins = "http://localhost:3001")
public class MetricsController {
    private final MetricsEndpoint metricsEndpoint;

    public MetricsController(MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }
    @GetMapping
    public ResponseEntity<Object> getMetrics() {
        Object metrics = metricsEndpoint.listNames();
        return ResponseEntity.ok(metrics);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Object> getMetricDetails(@PathVariable String name) {
        Object metricDetails = metricsEndpoint.metric(name, null);
        return ResponseEntity.ok(metricDetails);
    }



}
