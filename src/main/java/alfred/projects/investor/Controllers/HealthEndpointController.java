package alfred.projects.investor.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthEndpointController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthEndpoint () {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
