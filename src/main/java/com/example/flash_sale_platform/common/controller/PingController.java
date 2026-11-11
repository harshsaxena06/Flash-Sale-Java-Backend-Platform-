package com.example.flash_sale_platform.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Temporary connectivity check")
public class PingController {

    @GetMapping("/ping")
    @Operation(summary = "Returns app status and server time in UTC")
    public Map<String, Object> ping() {
        return Map.of(
                "status", "UP",
                "application", "flash-sale-platform",
                "serverTimeUtc", Instant.now().toString());
    }
}