package com.kirylshreyter.templatenotesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TemplatenotesapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplatenotesapiApplication.class, args);
    }
}
