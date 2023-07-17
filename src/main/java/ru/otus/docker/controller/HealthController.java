package ru.otus.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.docker.model.Health;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Health health() {
        return new Health("OK");
    }
}
