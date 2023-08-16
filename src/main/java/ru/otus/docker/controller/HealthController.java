package ru.otus.docker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.docker.model.Health;
import ru.otus.docker.model.InternetAddress;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Optional;

@RestController
public class HealthController {

//    curl -X GET -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/health
    @GetMapping("/health")
    public Health health() {
        return new Health("OK");
    }

//    curl -X GET -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/ip
    @GetMapping("/ip")
    public InternetAddress getIP() {
        try {
            return new InternetAddress(Inet4Address.getLocalHost(), Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

//    curl -X GET -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/error
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        Optional<String> s = Optional.empty();
        return ResponseEntity.ok(s
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, ""
                )));
    }
}
