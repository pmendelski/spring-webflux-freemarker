package com.sandbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
class PingController {
    @GetMapping("/ping")
    Mono<ResponseEntity<String>> ping() {
        return Mono.just(ResponseEntity.ok("pong"));
    }
}
