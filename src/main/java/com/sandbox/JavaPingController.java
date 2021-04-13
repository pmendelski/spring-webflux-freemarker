package com.sandbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class JavaPingController {
    @GetMapping("/java/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.ok("java-pong");
    }
}
