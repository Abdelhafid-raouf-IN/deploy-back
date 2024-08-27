package unibank.service.pilot.adapters.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator/metrics")
@CrossOrigin(origins = "http://localhost:3001")
@Tag(name = "Metrics", description = "Controller for metrics")
public class MetricsController {
    private final MetricsEndpoint metricsEndpoint;

    public MetricsController(MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }
    @Operation(summary = "Get Metrics")
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
