package com.mindlift.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("app", "MindLift API", "status", "OK");
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "OK", "message", "Backend local MindLift funcionando");
    }
}
