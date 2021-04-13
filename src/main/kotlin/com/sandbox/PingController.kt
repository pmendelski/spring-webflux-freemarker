package com.sandbox;

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @GetMapping("/kotlin/ping")
    fun ping(): ResponseEntity<String> {
        return ResponseEntity.ok("kotlin-pong")
    }
}
