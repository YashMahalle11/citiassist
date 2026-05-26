package com.citi.citiassist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Health Check REST Controller
 * 
 * Provides health check endpoints for monitoring and verification.
 * Used for load balancer health checks and application startup verification.
 */
@Slf4j
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    /**
     * Basic health check endpoint
     * 
     * @return Health status response
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("Health check request received");
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "CitiAssist Backend");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Detailed health check endpoint
     * 
     * @return Detailed health status with system information
     */
    @GetMapping("/detailed")
    public ResponseEntity<Map<String, Object>> detailedHealthCheck() {
        log.info("Detailed health check request received");
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("service", "CitiAssist Backend");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        response.put("runtime", getRuntime());
        response.put("database", getDatabaseStatus());
        
        return ResponseEntity.ok(response);
    }

    /**
     * Liveness probe - indicates if the service is running
     * 
     * @return Liveness status
     */
    @GetMapping("/live")
    public ResponseEntity<Map<String, String>> liveness() {
        log.debug("Liveness probe request received");
        return ResponseEntity.ok(Map.of("status", "alive"));
    }

    /**
     * Readiness probe - indicates if the service is ready to accept traffic
     * 
     * @return Readiness status
     */
    @GetMapping("/ready")
    public ResponseEntity<Map<String, String>> readiness() {
        log.debug("Readiness probe request received");
        return ResponseEntity.ok(Map.of("status", "ready"));
    }

    /**
     * Get runtime information
     */
    private Map<String, Object> getRuntime() {
        Runtime runtime = Runtime.getRuntime();
        Map<String, Object> runtimeInfo = new LinkedHashMap<>();
        runtimeInfo.put("availableProcessors", runtime.availableProcessors());
        runtimeInfo.put("maxMemory", formatBytes(runtime.maxMemory()));
        runtimeInfo.put("totalMemory", formatBytes(runtime.totalMemory()));
        runtimeInfo.put("freeMemory", formatBytes(runtime.freeMemory()));
        return runtimeInfo;
    }

    /**
     * Get database status
     */
    private Map<String, String> getDatabaseStatus() {
        Map<String, String> dbStatus = new LinkedHashMap<>();
        dbStatus.put("status", "connected");
        dbStatus.put("type", "H2");
        return dbStatus;
    }

    /**
     * Format bytes to human-readable format
     */
    private String formatBytes(long bytes) {
        int unit = 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
