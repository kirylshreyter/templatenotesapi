package com.kirylshreyter.templatenotesapi.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/errors")
public class ErrorController {
    @GetMapping("/unauthorized")
    public ResponseEntity<?> noAuth() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "unauthorized");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
